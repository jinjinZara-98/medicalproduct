package capstonedesign.medicalproduct.controller;

import capstonedesign.medicalproduct.dto.order.OrderSearch;
import capstonedesign.medicalproduct.dto.order.*;
import capstonedesign.medicalproduct.security.member.MemberInfo;
import capstonedesign.medicalproduct.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("order/cartItems")
    public String orderCartItems(@AuthenticationPrincipal MemberInfo member, OrderRequestDto orderRequestDto, Model model) {

        orderRequestDto.setName(member.getName());
        orderRequestDto.setPhoneNumber(member.getPhoneNumber()); orderRequestDto.setAddress(member.getAddress());
        orderRequestDto.setAddressDetail(member.getAddressDetail()); orderRequestDto.setAccountHost(member.getAccountHost());
        orderRequestDto.setBankName(member.getBankName()); orderRequestDto.setAccountNumber(member.getAccountNumber());

        List<OrderItemRequestDto> orderItems = orderRequestDto.getOrderItemRequestDtos();

        model.addAttribute("orderDto", orderRequestDto);
        model.addAttribute("orderItemDto", orderItems);

        return "orders/order";
    }

    @PostMapping("order/item")
    public String order(@AuthenticationPrincipal MemberInfo member, OrderRequestDto orderRequestDto, Model model){

        orderRequestDto.setName(member.getName());
        orderRequestDto.setPhoneNumber(member.getPhoneNumber()); orderRequestDto.setAddress(member.getAddress());
        orderRequestDto.setAddressDetail(member.getAddressDetail()); orderRequestDto.setAccountHost(member.getAccountHost());
        orderRequestDto.setBankName(member.getBankName()); orderRequestDto.setAccountNumber(member.getAccountNumber());

        List<OrderItemRequestDto> orderItemDto = orderRequestDto.getOrderItemRequestDtos();

        model.addAttribute("orderDto",  orderRequestDto);
        model.addAttribute("orderItemDto", orderItemDto);

        return "orders/order";
    }

    @PostMapping("order/payment")
    public String orderPro(@AuthenticationPrincipal MemberInfo member, Model model,
                           @Valid @ModelAttribute("orderDto") OrderRequestDto orderDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()) {

            model.addAttribute("orderItemDto", orderDto.getOrderItemRequestDtos());

            return "orders/order";
        }

        orderService.save(member.getId(), orderDto);

        return "redirect:/";
    }

    @GetMapping("order/orderList")
    public String orderList(@AuthenticationPrincipal MemberInfo member,
                            @ModelAttribute("orderSearch") OrderSearch orderSearch, Model model){

        List<OrderItemResponseDto> orderedItems = orderService.findAllOrderItemByMemberIdAndOrderInfo(member.getId(), orderSearch);

        model.addAttribute("orderedItems", orderedItems);

        return "orders/orderList";
    }

    @GetMapping("order/{id}/detail")
    public String orderDetail(@PathVariable("id") long orderId, Model model){

        List<OrderItemResponseDto> orderedItems = orderService.findAllOrderItemById(orderId);

        model.addAttribute("orderedItems", orderedItems);

        RecipientInfoDto recipientInfo = orderService.findById(orderId);

        model.addAttribute("recipientInfo", recipientInfo);

        return "orders/orderDetail";
    }

    @PostMapping("order/{id}/tryCancel")
    public String tryOrderCancel(@PathVariable("id") long orderId, Model model) {

        List<OrderItemResponseDto> orderedItems = orderService.findAllOrderItemById(orderId);

        model.addAttribute("orderId", orderId);
        model.addAttribute("orderedItems", orderedItems);

        return "orders/orderCancel";
    }

    @PostMapping("order/{id}/cancel")
    public String orderCancel(@PathVariable("id") long orderId) {

        orderService.cancel(orderId);

        return "redirect:/order/orderList";
    }
}