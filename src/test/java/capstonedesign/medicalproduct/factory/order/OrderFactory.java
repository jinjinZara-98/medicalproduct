package capstonedesign.medicalproduct.factory.order;

import capstonedesign.medicalproduct.domain.OrderStatus;
import capstonedesign.medicalproduct.domain.entity.Cart;
import capstonedesign.medicalproduct.domain.entity.Item;
import capstonedesign.medicalproduct.dto.order.OrderItemRequestDto;
import capstonedesign.medicalproduct.dto.order.OrderItemResponseDto;
import capstonedesign.medicalproduct.dto.order.OrderRequestDto;
import capstonedesign.medicalproduct.factory.BasicFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderFactory extends BasicFactory {

    public static OrderItemRequestDto makeOrderItemRequestDto(long cartId, long itemId) {
        return new OrderItemRequestDto((int) cartId, itemId, "수술가운", "http://www.yolomarket.kr/data/item/G171103/thumb-4A06002040_L_250x250.jpg"
                , 1, 20000, 20000);
    }

    public static OrderRequestDto makeOrderRequestDto(Cart cart, Item item) {
        List<OrderItemRequestDto> orderItemDtos = new ArrayList<>();
        orderItemDtos.add(makeOrderItemRequestDto(cart.getId(), item.getId()));

        return new OrderRequestDto("홍길동", "01012341234", "충북 충주시 대소원면 대학로 50",
                "예성생활관", "", "홍길동", "농협", "3520459732493", orderItemDtos);
    }

    public static List<OrderItemResponseDto> makeOrderItemResponseDtoList() {
        List<OrderItemResponseDto> orderResponseDtos = new ArrayList<>();

        orderResponseDtos.add(new OrderItemResponseDto(1L , "수술가운", "http://www.yolomarket.kr/data/item/G171103/thumb-4A06002040_L_250x250.jpg",
                LocalDateTime.now(), 1L, 1, 20000, OrderStatus.ORDER, null));

        return orderResponseDtos;
    }
}
