package capstonedesign.medicalproduct.domain.entity;

import capstonedesign.medicalproduct.domain.Information;
import capstonedesign.medicalproduct.domain.OrderStatus;
import capstonedesign.medicalproduct.dto.order.OrderRequestDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="name", column=@Column(name = "recipientName")),
            @AttributeOverride(name="phoneNumber", column=@Column(name = "recipientPhoneNumber")),
            @AttributeOverride(name="address", column=@Column(name = "recipientAddress")),
            @AttributeOverride(name="addressDetail", column=@Column(name = "recipientAddressDetail")),
            @AttributeOverride(name="accountHost", column=@Column(name = "orderAccountHost")),
            @AttributeOverride(name="bankName", column=@Column(name = "orderBankName")),
            @AttributeOverride(name="accountNumber", column=@Column(name = "orderAccountNumber"))
    })
    private Information information;

    @Column
    private String deliveryMessage;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Order(Information information, String deliveryMessage, LocalDateTime orderDate,
                 OrderStatus status, Member member) {
        this.information = information;
        this.deliveryMessage = deliveryMessage;
        this.orderDate = orderDate;
        this.status = status;
        this.member = member;
    }

    public Order(Information information, String deliveryMessage, LocalDateTime orderDate,
                OrderStatus status, List<OrderItem> orderItems, Member member) {
        this.information = information;
        this.deliveryMessage = deliveryMessage;
        this.orderDate = orderDate;
        this.status = status;
        this.orderItems = orderItems;
        this.member = member;
    }

    @Builder
    public Order(Information information, String deliveryMessage,
                 LocalDateTime orderDate, OrderStatus status) {
        this.information = information;
        this.deliveryMessage = deliveryMessage;
        this.orderDate = orderDate;
        this.status = status;
    }

    public void cancelOrder() {
        this.status = OrderStatus.CANCEL;
    }

    public void updateMember(Member member) {
        this.member = member;
        if(member.getOrders() != null) {
            member.getOrders().add(this);
        }
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.inputOrder(this);
    }

    public static Order createOrder(Member member, OrderRequestDto orderDto, List<OrderItem> orderItems) {

        Order order = Order.builder()
                    .information(new Information(orderDto.getName(), orderDto.getPhoneNumber(), orderDto.getAddress()
                                                ,orderDto.getAddressDetail(), orderDto.getAccountHost()
                                                ,orderDto.getBankName(), orderDto.getAccountNumber()))
                    .deliveryMessage(orderDto.getDeliveryMessage())
                    .orderDate(LocalDateTime.now())
                    .status(OrderStatus.ORDER).build();

        order.updateMember(member);

        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }

        return order;
    }

    public void cancel() {
        this.cancelOrder();
    }
}
