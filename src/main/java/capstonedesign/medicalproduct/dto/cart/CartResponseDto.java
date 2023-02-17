package capstonedesign.medicalproduct.dto.cart;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class CartResponseDto {

    private long id;

    private long itemId;

    private String name;

    private String imageSrc;

    private int price;

    private int quantity;

    @QueryProjection
    public CartResponseDto(long id, long itemId, String name, String imageSrc, int price, int quantity) {
        this.id = id;
        this.itemId = itemId;
        this.name = name;
        this.imageSrc = imageSrc;
        this.price = price;
        this.quantity = quantity;
    }
}