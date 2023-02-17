package capstonedesign.medicalproduct.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequestDto {

    private Integer cartId;

    private long itemId;

    private String name;

    private String imageSrc;

    private int quantity;

    private int price;

    private int totalPrice;
}