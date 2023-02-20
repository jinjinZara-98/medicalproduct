package capstonedesign.medicalproduct.service;

import capstonedesign.medicalproduct.domain.entity.Item;
import capstonedesign.medicalproduct.dto.item.ItemSearch;
import capstonedesign.medicalproduct.dto.item.ItemDetailDto;
import capstonedesign.medicalproduct.dto.item.ItemDto;
import capstonedesign.medicalproduct.exception.ItemNotFoundException;
import capstonedesign.medicalproduct.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Page<ItemDto> findAllByName(ItemSearch itemSearch, int page) {


        return itemRepository.findAllByName(itemSearch, PageRequest.of(page,8, Sort.Direction.ASC, "id"));

    }

    public Page<ItemDto> findAllByCategory(String category, int page) {

        return itemRepository.findAllByCategory(category, PageRequest.of(page,8, Sort.Direction.ASC, "id"));
    }

    public ItemDetailDto findById(long itemId) {

        Item item =  itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException("해당 상품은 없습니다. id" + itemId));

        return new ItemDetailDto(item);
    }
}
