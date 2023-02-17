package capstonedesign.medicalproduct.factory.item;

import capstonedesign.medicalproduct.dto.item.ItemDto;
import capstonedesign.medicalproduct.factory.BasicFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public class ItemFactory extends BasicFactory {

    public static Page<ItemDto> makePageItemDto() {
        ItemDto itemDto = new ItemDto(1L,"수술가운", "http://www.yolomarket.kr/data/item/G171103/thumb-4A06002040_L_250x250.jpg",20000);

        List<ItemDto> itemDtos = new ArrayList<>();
        itemDtos.add(itemDto);

        return new PageImpl<>(itemDtos, PageRequest.of(0,8, Sort.Direction.ASC, "id"), 1);
    }
}
