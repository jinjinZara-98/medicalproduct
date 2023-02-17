package capstonedesign.medicalproduct.domain.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "ordereditem_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    Item item;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private int orderPrice;

    public void inputOrder(Order order) {
        this.order = order;
    }

    public OrderItem(Order order, Item item, int quantity, int orderPrice) {
        this.order = order;
        this.item = item;
        this.quantity = quantity;
        this.orderPrice = orderPrice;
    }

    @Builder
    public OrderItem(Item item, int quantity, int orderPrice) {
        this.item = item;
        this.quantity = quantity;
        this.orderPrice = orderPrice;
    }

    public static OrderItem createOrderItem(Item item, int orderPrice, int quantity) {

        OrderItem orderItem = OrderItem.builder()
                .item(item)
                .orderPrice(orderPrice)
                .quantity(quantity)
                .build();

        return orderItem;
    }

    public int getTotalPrice() {

        return getOrderPrice() * getQuantity();
    }
}
