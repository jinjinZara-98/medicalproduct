package capstonedesign.medicalproduct.service.integration;

import capstonedesign.medicalproduct.dto.item.ItemDetailDto;
import capstonedesign.medicalproduct.dto.item.ItemSearch;
import capstonedesign.medicalproduct.dto.item.ItemDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;

class ItemServiceIntegrationTest extends ServiceIntegrationTest{

    @Test
    @DisplayName("검색한 이름에 해당하는 상품을 조회한다.")
    public void findAllByName() throws Exception {

        //given
        ItemSearch itemSearch = new ItemSearch("가위");

        //when
        Page<ItemDto> items =
               itemService.findAllByName(itemSearch, 0);

        //then
        Assertions.assertEquals(1, items.getTotalPages());
        Assertions.assertEquals(2, items.getContent().size());
    }

    @Test
    @DisplayName("카테고리에 속한 상품을 조회한다.")
    public void findAllByCategory() throws Exception {

        //given

        //when
        Page<ItemDto> items =
                 itemService.findAllByCategory("의료기기", 0);

        //then
        Assertions.assertEquals(2, items.getTotalPages());
        Assertions.assertEquals(8, items.getContent().size());
    }

    @Test
    @DisplayName("하나의 상품을 조회한다.")
    public void findById() throws Exception {

        //given

        //when
        ItemDetailDto itemDetailDto = itemService.findById(item.getId());

        //then
        Assertions.assertEquals("수술가운", itemDetailDto.getName());
        Assertions.assertEquals(20000, itemDetailDto.getPrice());
    }
}