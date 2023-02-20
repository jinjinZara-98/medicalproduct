package capstonedesign.medicalproduct.factory;

import capstonedesign.medicalproduct.domain.Information;
import capstonedesign.medicalproduct.domain.MemberRole;
import capstonedesign.medicalproduct.domain.OrderStatus;
import capstonedesign.medicalproduct.domain.entity.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BasicFactory {
    public static Information makeInformation() {
        return new Information("홍길동", "01012341234", "충북 충주시 대소원면 대학로 50",
                "예성생활관", "홍길동", "농협", "3520459732493");
    }

    public static Member makeTestMember() {
        Member member = new Member("gildong123", "gildong", makeInformation(),"gildong123@naver.com", MemberRole.ROLE_USER);

        ReflectionTestUtils.setField(member, "id", 1L);

        return member;
    }

    public static Member makeTestMember2() {
        Information information = new Information("임꺽정", "01012345678", "충북 충주시 대소원면 대학로 50",
                "대원생활관", "임꺽정", "농협", "3520459732493");

        Member member = new Member("jeong123", "jeong", information,"jeong123@naver.com", MemberRole.ROLE_USER);

        ReflectionTestUtils.setField(member, "id", 2L);

        return member;
    }

    public static Item makeTestItem() {
        Item item = new Item("의료소모품", "http://www.yolomarket.kr/data/item/G171103/thumb-4A06002040_L_250x250.jpg",
                "수술시 시술자가 입는 입는 내의입니다. 린넨 소재이므로 세탁 후 재사용이 가능합니다.", "수술가운", 20000);

        ReflectionTestUtils.setField(item, "id", 1L);

        return item;
    }

    public static Cart makeTestCart(Member member, Item item) {
        Cart cart = new Cart(1, member, item);

        ReflectionTestUtils.setField(cart, "id", 1L);

        return cart;
    }

    public static Cart makeTestCart2(Member member, Item item) {
        Cart cart = new Cart(1, member, item);

        ReflectionTestUtils.setField(cart, "id", 2L);

        return cart;
    }

    public static List<OrderItem> makeTestOrderItem(Order order, Item item) {
        OrderItem orderItem = new OrderItem(order, item, 1, 20000);

        ReflectionTestUtils.setField(orderItem, "id", 1L);

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem);

        return orderItems;
    }

    public static Order makeTestOrder(List<OrderItem> orderItems, Member member) {
        Order order = new Order(makeInformation(), "", LocalDateTime.now(), OrderStatus.ORDER,
                                orderItems, member);

        ReflectionTestUtils.setField(order, "id", 1L);

        return order;
    }

    public static Order makeTestOrder2 (List<OrderItem> orderItems, Member member) {
        Order order = new Order(makeInformation(), "", LocalDateTime.now(), OrderStatus.ORDER,
                orderItems, member);

        ReflectionTestUtils.setField(order, "id", 2L);

        return order;
    }

    public static Review makeTestReview (Member member, Item item) {
        Review review = new Review("좋아요.", "잘 받았습니다.", member, item,
                "9780b5f-2f60-4aca-954b-c5b6d8f3206a.jfif", "images.png", LocalDateTime.now());

        ReflectionTestUtils.setField(review, "id", 1L);

        return review;
    }

    public static Review makeTestReview2 (Member member, Item item) {
        Review review = new Review("배송이 빠르네요.", "잘 쓰겠습니다.", member, item,
                "1780b5f-2f60-4aca-954b-c5b6d8f3206a.jfif", "images.png", LocalDateTime.now());

        ReflectionTestUtils.setField(review, "id", 2L);

        return review;
    }
}
