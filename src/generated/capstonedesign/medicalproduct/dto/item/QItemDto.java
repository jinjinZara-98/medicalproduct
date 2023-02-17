package capstonedesign.medicalproduct.dto.item;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * capstonedesign.medicalproduct.dto.item.QItemDto is a Querydsl Projection type for ItemDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QItemDto extends ConstructorExpression<ItemDto> {

    private static final long serialVersionUID = 2000249235L;

    public QItemDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> imageSrc, com.querydsl.core.types.Expression<Integer> price) {
        super(ItemDto.class, new Class<?>[]{long.class, String.class, String.class, int.class}, id, name, imageSrc, price);
    }

}
