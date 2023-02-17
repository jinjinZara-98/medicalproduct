package capstonedesign.medicalproduct.repository.item;

import capstonedesign.medicalproduct.dto.item.ItemSearch;
import capstonedesign.medicalproduct.domain.entity.QItem;
import capstonedesign.medicalproduct.dto.item.ItemDto;
import capstonedesign.medicalproduct.dto.item.QItemDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
public class ItemCustomRepositoryImpl implements ItemCustomRepository{

    private final JPAQueryFactory queryFactory;

    public Page<ItemDto> findAllByName(ItemSearch itemSearch, Pageable pageable) {

        QItem i = QItem.item;

        String itemName = itemSearch.getItemName();

        BooleanBuilder builder = new BooleanBuilder();

        if(hasText(itemSearch.getItemName())) {
            builder.and(i.name.contains(itemName));
        }

        QueryResults<ItemDto> findItem = queryFactory
                .select(new QItemDto(i.id, i.name, i.imageSrc, i.price))
                .from(i)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<ItemDto> content = findItem.getResults();

        long total = findItem.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    public Page<ItemDto> findAllByCategory(String category, Pageable pageable) {

        QItem i = QItem.item;

        QueryResults<ItemDto> findItem = queryFactory
                .select(new QItemDto(i.id, i.name, i.imageSrc, i.price))
                .from(i)
                .where(i.category.eq(category))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<ItemDto> content = findItem.getResults();

        long total = findItem.getTotal();

        return new PageImpl<>(content, pageable, total);
    }
}
