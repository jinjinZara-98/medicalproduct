package capstonedesign.medicalproduct.service.integration;

import capstonedesign.medicalproduct.dto.order.OrderSearch;
import capstonedesign.medicalproduct.domain.OrderStatus;
import capstonedesign.medicalproduct.dto.order.OrderItemResponseDto;
import capstonedesign.medicalproduct.dto.order.OrderRequestDto;
import capstonedesign.medicalproduct.dto.order.RecipientInfo;
import capstonedesign.medicalproduct.factory.order.OrderFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;


class OrderServiceIntegrationTest extends ServiceIntegrationTest{

    @Test
    @DisplayName("주문을 해서 상품을 구매한다.")
    public void save() {

        //given
        OrderRequestDto orderRequestDto = OrderFactory.makeOrderRequestDto(cart, item);

        //when
        long orderId = orderService.save(member.getId(), orderRequestDto);
        RecipientInfo recipientInfo = orderService.findById(orderId);

        //then
        Assertions.assertEquals(order.getInformation().getName(), recipientInfo.getRecipientName());
    }

    @Test
    @DisplayName("주문 상품 수령자를 확인한다.")
    public void findById() {

        //given

        //when
        RecipientInfo recipientInfo = orderService.findById(order.getId());

        //then
        Assertions.assertEquals(order.getInformation().getName(), recipientInfo.getRecipientName());
    }

    @Test
    @DisplayName("주문 상태에 따른 주문 상품 리스트를 가져온다.")
    public void findAllOrderItemByMemberIdAndOrderInfo() {

        //given
        OrderSearch orderSearch = new OrderSearch("", OrderStatus.ORDER);

        //when
        List<OrderItemResponseDto> orderItemResponseDtos =
                orderService.findAllOrderItemByMemberIdAndOrderInfo(member.getId(), orderSearch);

        //then
        Assertions.assertEquals(1, orderItemResponseDtos.size());
    }

    @Test
    @DisplayName("주문 번호에 해당하는 주문 상품을 가져온다.")
    public void findAllOrderItemById() {

        //given

        //when
        List<OrderItemResponseDto> orderItemResponseDtos =
                orderService.findAllOrderItemById(order.getId());

        //then
        Assertions.assertEquals(1, orderItemResponseDtos.size());
    }

    @Test
    @DisplayName("주문을 취소한다.")
    public void cancel() {

        //given

        //when
        orderService.cancel(order.getId());

        OrderSearch orderSearch = new OrderSearch("", OrderStatus.CANCEL);

        List<OrderItemResponseDto> orderItemResponseDtos =
                orderService.findAllOrderItemByMemberIdAndOrderInfo(member.getId(), orderSearch);

        //then
        Assertions.assertEquals(1, orderItemResponseDtos.size());
    }
}