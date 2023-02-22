package capstonedesign.medicalproduct.dto.item;

import capstonedesign.medicalproduct.domain.entity.Item;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

@Data
@NoArgsConstructor
public class ItemDetailDto {

    private long id;

    private String name;

    private String imageSrc;

    private int quantity;

    @NumberFormat(pattern = "###,###")
    private int price;

    private int totalPrice;

    private String introduction;

    public ItemDetailDto(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.imageSrc = item.getImageSrc();
        this.quantity = 1;
        this.price = item.getPrice();
        this.totalPrice = item.getPrice();
        this.introduction = item.getIntroduction();
    }
}
