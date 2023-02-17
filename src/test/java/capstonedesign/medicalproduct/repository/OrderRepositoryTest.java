package capstonedesign.medicalproduct.repository;

import capstonedesign.medicalproduct.domain.MemberRole;
import capstonedesign.medicalproduct.dto.item.OrderSearch;
import capstonedesign.medicalproduct.domain.OrderStatus;
import capstonedesign.medicalproduct.domain.entity.Item;
import capstonedesign.medicalproduct.domain.entity.Member;
import capstonedesign.medicalproduct.domain.entity.Order;
import capstonedesign.medicalproduct.domain.entity.OrderItem;
import capstonedesign.medicalproduct.dto.order.OrderItemResponseDto;
import capstonedesign.medicalproduct.factory.member.MemberFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class OrderRepositoryTest extends RepositoryTest{

    Member member;
    Item item;
    Order order;

    @BeforeEach
    void beforeEach() {
        member = memberRepository.save(new Member("gildong123", "jin", MemberFactory.makeInformation(), "gildong123@naver.com",
                MemberRole.ROLE_USER));

        item = itemRepository.save(new Item("의료소모품", "http://www.yolomarket.kr/data/item/G171103/thumb-4A06002040_L_250x250.jpg",
                "수술시 시술자가 입는 입는 내의입니다. 린넨 소재이므로 세탁 후 재사용이 가능합니다.", "수술가운", 20000));

        OrderItem orderItem = new OrderItem(item, 1, 20000);

        Order createdOrder = new Order(MemberFactory.makeInformation(), "", LocalDateTime.now(), OrderStatus.ORDER, member);
        createdOrder.addOrderItem(orderItem);

        order = orderRepository.save(createdOrder);

        OrderSearch orderSearch = new OrderSearch("수술가운", OrderStatus.ORDER);
    }

    @Test
    @DisplayName("주문 상태에 따른 주문 상품 리스트를 가져온다.")
    public void findAllOrderItemByMemberIdAndOrderInfo() {

        //given
        OrderSearch orderSearch = new OrderSearch("수술가운", OrderStatus.ORDER);

        //when
        List<OrderItemResponseDto> orderResponseDtos =
                orderRepository.findAllOrderItemByMemberIdAndOrderInfo(member.getId(), orderSearch);

        //then
        Assertions.assertEquals("수술가운", orderResponseDtos.get(0).getName());
    }

    @Test
    @DisplayName("주문 번호에 해당하는 주문 상품을 가져온다.")
    public void findAllOrderItemById() {
        //given

        //when
        List<OrderItemResponseDto> orderResponseDtos =
                orderRepository.findAllOrderItemById(order.getId());

        //then
        Assertions.assertEquals("수술가운", orderResponseDtos.get(0).getName());
    }
}
