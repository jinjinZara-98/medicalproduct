package capstonedesign.medicalproduct.service.unit;

import capstonedesign.medicalproduct.domain.entity.Cart;
import capstonedesign.medicalproduct.domain.entity.Item;
import capstonedesign.medicalproduct.domain.entity.Member;
import capstonedesign.medicalproduct.dto.cart.CartResponseDto;
import capstonedesign.medicalproduct.factory.cart.CartFactory;
import capstonedesign.medicalproduct.factory.item.ItemFactory;
import capstonedesign.medicalproduct.factory.member.MemberFactory;
import capstonedesign.medicalproduct.repository.cart.CartRepository;
import capstonedesign.medicalproduct.repository.item.ItemRepository;
import capstonedesign.medicalproduct.repository.member.MemberRepository;
import capstonedesign.medicalproduct.service.CartService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CartServiceUnitTest {

    Member member;
    Item item;
    Cart cart;

    @InjectMocks
    CartService cartService;

    @Mock
    CartRepository  cartRepository;

    @Mock
    ItemRepository itemRepository;

    @Mock
    MemberRepository memberRepository;

    @BeforeEach
    public void init() {
        member = MemberFactory.makeTestMember();
        item = ItemFactory.makeTestItem();
        cart = CartFactory.makeTestCart(member, item);
    }

    @Test
    @DisplayName("장바구니에 상품을 담는다.")
    public void save() {
        //given
        Cart savedCart = CartFactory.makeTestCart2(member, item);

        //mocking
        given(cartRepository.save(any())).willReturn(savedCart);
        given(memberRepository.findById(member.getId())).willReturn(Optional.ofNullable(member));
        given(itemRepository.findById(item.getId())).willReturn(Optional.ofNullable(item));

        //when
        long cartId = cartService.save(member.getId(), item.getId(), 1);

        //then
        Assertions.assertEquals(2L, cartId);
    }

    @Test
    @DisplayName("장바구니에 있는 상품을 확인한다.")
    public void findAllByMemberId () {

        //given

        //mocking
        given(cartRepository.findAllByMemberId(member.getId())).willReturn(CartFactory.makeCartResponseDto());

        //when
        List<CartResponseDto> cartResponseDtos = cartService.findAllByMemberId(member.getId());

        //then
        Assertions.assertEquals("수술가운", cartResponseDtos.get(0).getName());
    }

    @Test
    @DisplayName("장바구니에 있는 상품 수량을 증가시킨다.")
    public void increaseQuantity(){

        //given

        //mocking
        given(cartRepository.findById(cart.getId())).willReturn(Optional.ofNullable(cart));

        //when
        cartService.increaseQuantity(cart.getId());

        //then
        verify(cartRepository, times(1)).findById(cart.getId());
    }

    //장바구니 상품 리스트에서 상품 수량 마이너스 버튼 눌렀을때
    @Test
    @DisplayName("장바구니에 있는 상품 수량을 감소시킨다.")
    public void decreaseQuantity(){

        //given

        //mocking
        given(cartRepository.findById(cart.getId())).willReturn(Optional.ofNullable(cart));
        willDoNothing().given(cartRepository).delete(cart);

        //when
        cartService.decreaseQuantity(cart.getId());

        //then
        //cartService 의 decreaseQuantity()사용하면
        //cartRepository findById() 와 delete() 1번씩 실행되었는지
        verify(cartRepository, times(1)).findById(cart.getId());
        verify(cartRepository, times(1)).delete(cart);
    }

    @Test
    @DisplayName("장바구니에 있는 상품을 삭제한다.")
    public void delete(){

        //given

        //mocking
        given(cartRepository.findById(cart.getId())).willReturn(Optional.ofNullable(cart));
        willDoNothing().given(cartRepository).delete(cart);

        //when, then
        Assertions.assertDoesNotThrow(() -> cartService.delete(cart.getId()));
    }
}
