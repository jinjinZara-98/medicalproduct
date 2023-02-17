package capstonedesign.medicalproduct.dto.order;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * capstonedesign.medicalproduct.dto.order.QOrderItemResponseDto is a Querydsl Projection type for OrderItemResponseDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QOrderItemResponseDto extends ConstructorExpression<OrderItemResponseDto> {

    private static final long serialVersionUID = -1661633889L;

    public QOrderItemResponseDto(com.querydsl.core.types.Expression<Long> itemId, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> imageSrc, com.querydsl.core.types.Expression<java.time.LocalDateTime> orderDate, com.querydsl.core.types.Expression<Long> orderId, com.querydsl.core.types.Expression<Integer> quantity, com.querydsl.core.types.Expression<Integer> price, com.querydsl.core.types.Expression<capstonedesign.medicalproduct.domain.OrderStatus> status, com.querydsl.core.types.Expression<Long> reviewId) {
        super(OrderItemResponseDto.class, new Class<?>[]{long.class, String.class, String.class, java.time.LocalDateTime.class, long.class, int.class, int.class, capstonedesign.medicalproduct.domain.OrderStatus.class, long.class}, itemId, name, imageSrc, orderDate, orderId, quantity, price, status, reviewId);
    }

    public QOrderItemResponseDto(com.querydsl.core.types.Expression<Long> itemId, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> imageSrc, com.querydsl.core.types.Expression<java.time.LocalDateTime> orderDate, com.querydsl.core.types.Expression<Long> orderId, com.querydsl.core.types.Expression<Integer> quantity, com.querydsl.core.types.Expression<Integer> price, com.querydsl.core.types.Expression<capstonedesign.medicalproduct.domain.OrderStatus> status) {
        super(OrderItemResponseDto.class, new Class<?>[]{long.class, String.class, String.class, java.time.LocalDateTime.class, long.class, int.class, int.class, capstonedesign.medicalproduct.domain.OrderStatus.class}, itemId, name, imageSrc, orderDate, orderId, quantity, price, status);
    }

}

