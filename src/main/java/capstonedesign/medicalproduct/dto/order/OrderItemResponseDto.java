package capstonedesign.medicalproduct.dto.order;

import capstonedesign.medicalproduct.domain.OrderStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDateTime;

@Data
public class OrderItemResponseDto {

    private long itemId;

    private String name;

    private String imageSrc;

    @DateTimeFormat(pattern = "yyyy년 MM월 dd일 HH시 mm분")
    private LocalDateTime orderDate;

    private long orderId;

    private int quantity;

    @NumberFormat(pattern = "###, ###")
    private int price;

    private OrderStatus status;

    private Long reviewId;

    @QueryProjection
    public OrderItemResponseDto(long itemId, String name, String imageSrc, LocalDateTime orderDate,
                          long orderId, int quantity, Integer price, OrderStatus status, Long reviewId) {
        this.itemId = itemId;
        this.name = name;
        this.imageSrc = imageSrc;
        this.orderDate = orderDate;
        this.orderId = orderId;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.reviewId = reviewId;
    }

    @QueryProjection
    public OrderItemResponseDto(long itemId, String name, String imageSrc, LocalDateTime orderDate,
                          long orderId, int quantity, Integer price, OrderStatus status) {
        this.itemId = itemId;
        this.name = name;
        this.imageSrc = imageSrc;
        this.orderDate = orderDate;
        this.orderId = orderId;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
    }
}
