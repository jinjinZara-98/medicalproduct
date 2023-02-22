package capstonedesign.medicalproduct.controller;

import capstonedesign.medicalproduct.dto.cart.CartResponseDto;
import capstonedesign.medicalproduct.dto.item.ItemDetailDto;
import capstonedesign.medicalproduct.security.member.MemberInfo;
import capstonedesign.medicalproduct.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("cart/{itemId}/itemInstantPut")
    public String instantAddTheItem(@AuthenticationPrincipal MemberInfo memberInfo, @PathVariable("itemId") long itemId){

        cartService.save(memberInfo.getId(), itemId, 1);

        return "redirect:/";
    }

    @GetMapping("cart/put")
    public String addTheItem(@AuthenticationPrincipal MemberInfo memberInfo,
                             @ModelAttribute("itemDetailDto") ItemDetailDto itemDetailDto) {

        cartService.save(memberInfo.getId(), itemDetailDto.getId(), itemDetailDto.getQuantity());

        return "redirect:/item/" + itemDetailDto.getId();
    }

    @GetMapping("cart")
    public String findAllByMemberId(@AuthenticationPrincipal MemberInfo memberInfo, Model model){

        List<CartResponseDto> cartItems = cartService.findAllByMemberId(memberInfo.getId());

        model.addAttribute("cartItems", cartItems);

        return "cart/cartItems";
    }

    @GetMapping("cart/cartItem/{id}/QuantityPlus")
    public String increaseQuantit(@PathVariable("id") Long cartId){

        cartService.increaseQuantity(cartId);

        return "redirect:/cart";
    }

    @GetMapping("cart/cartItem/{id}/QuantityMinus")
    public String decreaseQuantity(@PathVariable("id") Long cartId){

        cartService.decreaseQuantity(cartId);

        return "redirect:/cart";
    }

    @PostMapping("cart/{cartId}/cancel")
    public String delete(@PathVariable("cartId") Long cartId) {

        cartService.delete(cartId);
        return "redirect:/cart";
    }
}