package capstonedesign.medicalproduct.service.unit;

import capstonedesign.medicalproduct.dto.order.OrderSearch;
import capstonedesign.medicalproduct.domain.OrderStatus;
import capstonedesign.medicalproduct.domain.entity.*;
import capstonedesign.medicalproduct.dto.order.OrderItemResponseDto;
import capstonedesign.medicalproduct.dto.order.OrderRequestDto;
import capstonedesign.medicalproduct.dto.order.RecipientInfoDto;
import capstonedesign.medicalproduct.factory.cart.CartFactory;
import capstonedesign.medicalproduct.factory.item.ItemFactory;
import capstonedesign.medicalproduct.factory.member.MemberFactory;
import capstonedesign.medicalproduct.factory.order.OrderFactory;
import capstonedesign.medicalproduct.repository.cart.CartRepository;
import capstonedesign.medicalproduct.repository.item.ItemRepository;
import capstonedesign.medicalproduct.repository.member.MemberRepository;
import capstonedesign.medicalproduct.repository.order.OrderRepository;
import capstonedesign.medicalproduct.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;

@ExtendWith(MockitoExtension.class)
public class OrderServiceUnitTest {

    Member member;
    Item item;
    Cart cart;
    List<OrderItem> orderItems;
    Order order;

    @InjectMocks
    OrderService orderService;

    @Mock
    ItemRepository itemRepository;

    @Mock
    MemberRepository memberRepository;

    @Mock
    OrderRepository orderRepository;

    @Mock
    CartRepository cartRepository;

    @BeforeEach
    public void init() {
        member = MemberFactory.makeTestMember();
        item = ItemFactory.makeTestItem();
        cart = CartFactory.makeTestCart(member, item);
        orderItems = OrderFactory.makeTestOrderItem(order, item);
        order = OrderFactory.makeTestOrder(orderItems, member);
    }

    @Test
    @DisplayName("????????? ?????? ????????? ????????????.")
    public void save() {

        //given
        OrderRequestDto orderRequestDto = OrderFactory.makeOrderRequestDto(cart, item);
        Order createdOrder = OrderFactory.makeTestOrder2(orderItems, member);

        //mocking
        given(memberRepository.findById(any())).willReturn(Optional.ofNullable(member));
        willDoNothing().given(cartRepository).deleteById(any());
        given(itemRepository.findById(any())).willReturn(Optional.ofNullable(item));
        given(orderRepository.save(any())).willReturn(createdOrder);

        //when
        long orderId = orderService.save(member.getId(), orderRequestDto);

        //then
        Assertions.assertEquals(createdOrder.getId(), orderId);
    }

    @Test
    @DisplayName("?????? ?????? ???????????? ????????????.")
    public void findById() {

        //given

        //mocking
        given(orderRepository.findById(order.getId())).willReturn(Optional.ofNullable(order));

        //when
        RecipientInfoDto recipientInfo = orderService.findById(order.getId());

        //then
        Assertions.assertEquals("?????????", recipientInfo.getRecipientName());
    }

    @Test
    @DisplayName("?????? ????????? ?????? ?????? ?????? ???????????? ????????????.")
    public void findAllOrderItemByMemberIdAndOrderInfo() {

        //given
        OrderSearch orderSearch = new OrderSearch("????????????", OrderStatus.ORDER);
        List<OrderItemResponseDto> ExpectResult = OrderFactory.makeOrderItemResponseDtoList();

        //mocking
        given(orderRepository.findAllOrderItemByMemberIdAndOrderInfo(order.getId(), orderSearch))
                .willReturn(ExpectResult);

        //when
        List<OrderItemResponseDto> ActualResult =
                orderService.findAllOrderItemByMemberIdAndOrderInfo(order.getId(), orderSearch);

        //then
        Assertions.assertEquals(ExpectResult.get(0).getName(), ActualResult.get(0).getName());
    }

    @Test
    @DisplayName("?????? ????????? ???????????? ?????? ????????? ????????????.")
    public void findAllOrderItemById() {
        //given
        List<OrderItemResponseDto> ExpectResult = OrderFactory.makeOrderItemResponseDtoList();

        //mocking
        given(orderRepository.findAllOrderItemById(order.getId())).willReturn(ExpectResult);

        //when
        List<OrderItemResponseDto> ActualResult =
                orderService.findAllOrderItemById(order.getId());

        //then
        Assertions.assertEquals(ExpectResult.get(0).getName(), ActualResult.get(0).getName());
    }

    @Test
    @DisplayName("????????? ????????????.")
    public void cancel(){

        //given

        //mocking
        given(orderRepository.findById(order.getId())).willReturn(Optional.ofNullable(order));

        //when
        orderService.cancel(order.getId());

        //then
        Assertions.assertEquals(order.getStatus(), OrderStatus.CANCEL);
    }
}
