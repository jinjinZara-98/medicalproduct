package capstonedesign.medicalproduct.repository;

import capstonedesign.medicalproduct.domain.Information;
import capstonedesign.medicalproduct.domain.MemberRole;
import capstonedesign.medicalproduct.domain.entity.Cart;
import capstonedesign.medicalproduct.domain.entity.Item;
import capstonedesign.medicalproduct.domain.entity.Member;
import capstonedesign.medicalproduct.dto.cart.CartResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class CartRepositoryTest extends RepositoryTest{

    Member member;
    Item item;
    Cart cart;

    @BeforeEach
    void beforeEach() {
        Information information = new Information("홍길동", "01012341234", "충북 충주시 대소원면 대학로 50",
                "예성생활관", "홍길동", "농협", "3520459732493");

        member = memberRepository.save(new Member("gildong123", "jin", information, "gildong123@naver.com",
                MemberRole.ROLE_USER));

        item = itemRepository.save(new Item("의료소모품", "http://www.yolomarket.kr/data/item/G171103/thumb-4A06002040_L_250x250.jpg",
                "수술시 시술자가 입는 입는 내의입니다. 린넨 소재이므로 세탁 후 재사용이 가능합니다.", "수술가운", 20000));

        cart = cartRepository.save(new Cart(1, member, item));
    }

    @Test
    @DisplayName("장바구니에 있는 상품을 확인한다.")
    public void findAllByMemberId () {

        //given

        //when
        List<CartResponseDto> cartResponseDtos = cartRepository.findAllByMemberId(member.getId());

        //then
        Assertions.assertEquals("수술가운", cartResponseDtos.get(0).getName());
    }
}
