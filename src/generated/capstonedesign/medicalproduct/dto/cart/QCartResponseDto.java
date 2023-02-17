package capstonedesign.medicalproduct.dto.cart;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * capstonedesign.medicalproduct.dto.cart.QCartResponseDto is a Querydsl Projection type for CartResponseDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCartResponseDto extends ConstructorExpression<CartResponseDto> {

    private static final long serialVersionUID = 2079299954L;

    public QCartResponseDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<Long> itemId, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> imageSrc, com.querydsl.core.types.Expression<Integer> price, com.querydsl.core.types.Expression<Integer> quantity) {
        super(CartResponseDto.class, new Class<?>[]{long.class, long.class, String.class, String.class, int.class, int.class}, id, itemId, name, imageSrc, price, quantity);
    }

}

