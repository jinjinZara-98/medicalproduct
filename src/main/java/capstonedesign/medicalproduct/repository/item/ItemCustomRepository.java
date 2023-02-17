package capstonedesign.medicalproduct.repository.item;

import capstonedesign.medicalproduct.dto.item.ItemDto;
import capstonedesign.medicalproduct.dto.item.ItemSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemCustomRepository {

    Page<ItemDto> findAllByCategory(String category, Pageable pageable);
    Page<ItemDto> findAllByName(ItemSearch itemSearch, Pageable pageable);
}
