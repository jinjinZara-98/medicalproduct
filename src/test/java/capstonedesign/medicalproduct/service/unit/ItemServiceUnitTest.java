package capstonedesign.medicalproduct.service.unit;

import capstonedesign.medicalproduct.domain.entity.Item;
import capstonedesign.medicalproduct.dto.item.ItemDetailDto;
import capstonedesign.medicalproduct.dto.item.ItemDto;
import capstonedesign.medicalproduct.dto.item.ItemSearch;
import capstonedesign.medicalproduct.factory.item.ItemFactory;
import capstonedesign.medicalproduct.repository.item.ItemRepository;
import capstonedesign.medicalproduct.service.ItemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ItemServiceUnitTest {

    Item item;

    @InjectMocks
    ItemService itemService;

    @Mock
    ItemRepository itemRepository;

    @BeforeEach
    public void init() {
        item = ItemFactory.makeTestItem();
    }

    @Test
    @DisplayName("검색한 이름에 해당하는 상품을 조회한다.")
    public void findAllByName() {
        //given
        ItemSearch itemSearch = new ItemSearch("수술가운");

        //mocking
        given(itemRepository.findAllByName(itemSearch, PageRequest.of(0,8, Sort.Direction.ASC, "id")))
                .willReturn(ItemFactory.makePageItemDto());

        //when
        Page<ItemDto> itemDtos = itemService.findAllByName(itemSearch, 0);

        //then
        Assertions.assertEquals("수술가운", itemDtos.getContent().get(0).getName());
        Assertions.assertEquals(1, itemDtos.getTotalPages());
    }

    @Test
    @DisplayName("카테고리에 속한 상품을 조회한다.")
    public void findAllByCategory() {
        //given

        //mocking
        given(itemRepository.findAllByCategory("의료소모품", PageRequest.of(0,8, Sort.Direction.ASC, "id")))
                .willReturn(ItemFactory.makePageItemDto());

        //when
        Page<ItemDto> itemDtos = itemService.findAllByCategory("의료소모품", 0);

        //then
        Assertions.assertEquals("수술가운", itemDtos.getContent().get(0).getName());
        Assertions.assertEquals(1, itemDtos.getTotalPages());
    }

    @Test
    @DisplayName("하나의 상품을 조회한다.")
    public void findById() {
        //given

        //mocking
        given(itemRepository.findById(1L))
                .willReturn(Optional.ofNullable(item));

        //when
        ItemDetailDto itemDetailDto = itemService.findById(1L);

        //then
        Assertions.assertEquals("수술가운", itemDetailDto.getName());
    }
}
