package capstonedesign.medicalproduct.dto.item;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

@Data
public class ItemDto {

    private long id;

    private String name;

    private String imageSrc;

    @NumberFormat(pattern = "###, ###")
    private Integer price;

    @QueryProjection
    public ItemDto(long id, String name, String imageSrc, int price) {
        this.id = id;
        this.name = name;
        this.imageSrc = imageSrc;
        this.price = price;
    }
}
