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
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("order/cartItem")
    public String orderCartItems(@AuthenticationPrincipal MemberInfo member, OrderRequestDto orderDto, Model model) {

        orderDto.setName(member.getName());
        orderDto.setPhoneNumber(member.getPhoneNumber()); orderDto.setAddress(member.getAddress());
        orderDto.setAddressDetail(member.getAddressDetail()); orderDto.setAccountHost(member.getAccountHost());
        orderDto.setBankName(member.getBankName()); orderDto.setAccountNumber(member.getAccountNumber());

        List<OrderItemRequestDto> orderItems = orderDto.getOrderItemRequestDtos();

        model.addAttribute("orderDto", orderDto);
        model.addAttribute("orderItemDto", orderItems);

        return "orders/order";
    }

    @PostMapping("order/itemOne")
    public String order(@AuthenticationPrincipal MemberInfo member, OrderRequestDto orderDto, Model model){

        orderDto.setName(member.getName());
        orderDto.setPhoneNumber(member.getPhoneNumber()); orderDto.setAddress(member.getAddress());
        orderDto.setAddressDetail(member.getAddressDetail()); orderDto.setAccountHost(member.getAccountHost());
        orderDto.setBankName(member.getBankName()); orderDto.setAccountNumber(member.getAccountNumber());

        List<OrderItemRequestDto> orderItemDto = orderDto.getOrderItemRequestDtos();

        model.addAttribute("orderDto",  orderDto);
        model.addAttribute("orderItemDto", orderItemDto);

        return "orders/order";
    }

    @PostMapping("order/payment")
    public String orderPro(@AuthenticationPrincipal MemberInfo member,
                           @Valid @ModelAttribute("orderDto") OrderRequestDto orderDto, BindingResult bindingResult, Model model){

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