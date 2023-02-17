package capstonedesign.medicalproduct.repository.cart;

import capstonedesign.medicalproduct.domain.entity.QCart;
import capstonedesign.medicalproduct.domain.entity.QItem;
import capstonedesign.medicalproduct.domain.entity.QMember;
import capstonedesign.medicalproduct.dto.cart.CartResponseDto;
import capstonedesign.medicalproduct.dto.cart.QCartResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CartCustomRepositoryImpl implements CartCustomRepository {

    private final JPAQueryFactory queryFactory;

    public List<CartResponseDto> findAllByMemberId(Long memberId){

        QMember member = QMember.member;
        QItem item = QItem.item;
        QCart cart = QCart.cart;

        List<CartResponseDto> result = queryFactory
                .select(new QCartResponseDto(cart.id, cart.item.id, cart.item.name, item.imageSrc, item.price, cart.quantity))
                .from(cart)
                .join(cart.member, member)
                .join(cart.item, item)
                .where(cart.member.id.eq(memberId).and(cart.item.id.eq(item.id)))
                .fetch();

        return result;
    }
}
