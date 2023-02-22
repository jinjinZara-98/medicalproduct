package capstonedesign.medicalproduct.service;

import capstonedesign.medicalproduct.domain.entity.Item;
import capstonedesign.medicalproduct.domain.entity.Member;
import capstonedesign.medicalproduct.domain.entity.Order;
import capstonedesign.medicalproduct.domain.entity.OrderItem;
import capstonedesign.medicalproduct.dto.order.OrderSearch;
import capstonedesign.medicalproduct.dto.order.*;
import capstonedesign.medicalproduct.exception.ItemNotFoundException;
import capstonedesign.medicalproduct.exception.MemberNotFoundException;
import capstonedesign.medicalproduct.exception.OrderNotFoundException;
import capstonedesign.medicalproduct.repository.cart.CartRepository;
import capstonedesign.medicalproduct.repository.item.ItemRepository;
import capstonedesign.medicalproduct.repository.member.MemberRepository;
import capstonedesign.medicalproduct.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    @Transactional
    public long save(long memberId, OrderRequestDto orderDto) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("해당 회원은 없습니다. id = " + memberId));

        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemRequestDto orderInfo : orderDto.getOrderItemRequestDtos()) {

            if(orderInfo.getCartId() != null)
                cartRepository.deleteById(Long.valueOf(orderInfo.getCartId()));

            Item item = itemRepository.findById(orderInfo.getItemId())
                .orElseThrow(() -> new ItemNotFoundException("해당 상품은 없습니다. id = " + orderInfo.getItemId()));

            OrderItem orderItem = OrderItem.createOrderItem(item, orderInfo.getTotalPrice(), orderInfo.getQuantity());

            orderItems.add(orderItem);
        }

        Order order = Order.createOrder(member, orderDto, orderItems);

        return orderRepository.save(order).getId();
    }

    public RecipientInfoDto findById(long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("해당 주문은 없습니다. id = " + orderId));

        return new RecipientInfoDto(order);
    }


    public List<OrderItemResponseDto> findAllOrderItemByMemberIdAndOrderInfo(long memberId, OrderSearch orderSearch) {
        return orderRepository.findAllOrderItemByMemberIdAndOrderInfo(memberId, orderSearch);
    }

    public List<OrderItemResponseDto> findAllOrderItemById(long orderId) {
        return orderRepository.findAllOrderItemById(orderId);
    }

    @Transactional
    public void cancel(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("해당 주문은 없습니다. id = " + orderId));

        order.cancel();
    }
}
