package capstonedesign.medicalproduct.service.integration;

import capstonedesign.medicalproduct.dto.cart.CartResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

class CartServiceIntegrationTest extends ServiceIntegrationTest{

    @Test
    @DisplayName("장바구니에 상품을 담는다.")
    public void save() throws Exception {
        //수술가운

        //given

        //when
        cartService.save(member.getId(), item.getId(), 1);
        CartResponseDto cart =  cartService.findAllByMemberId(member.getId()).stream()
                .filter(cartResponseDto ->cartResponseDto.getName().equals("수술가운"))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        //then
        Assertions.assertEquals(item.getName(), cart.getName());
    }

    @Test
    @DisplayName("장바구니에 있는 상품을 확인한다.")
    public void findAllByMemberId() {

        //given

        //when
        List<CartResponseDto> cartResponseDtos = cartService.findAllByMemberId(member.getId());

        //then
        Assertions.assertEquals(1, cartResponseDtos.size());
        Assertions.assertEquals(cart.getId(), cartResponseDtos.get(0).getId());
    }

    @Test
    @DisplayName("장바구니에 있는 상품 수량을 증가시킨다.")
    public void increaseQuantity() {

        //given

        //when
        cartService.increaseQuantity(cart.getId());
        List<CartResponseDto> cartResponseDtos = cartService.findAllByMemberId(member.getId());

        //then
        Assertions.assertEquals(3, cartResponseDtos.get(0).getQuantity());
    }

    @Test
    @DisplayName("장바구니에 있는 상품 수량를 감소시킨다.")
    public void decreaseQuantity() {

        //given

        //when
        cartService.decreaseQuantity(cart.getId());
        List<CartResponseDto> cartResponseDtos = cartService.findAllByMemberId(member.getId());

        //then
        Assertions.assertEquals(1, cartResponseDtos.get(0).getQuantity());
    }

    @Test
    @DisplayName("장바구니에 있는 상품을 삭제한다.")
    public void cartItemDelete() {

        //given

        //when
        cartService.delete(cart.getId());
        List<CartResponseDto> cartResponseDtos = cartService.findAllByMemberId(cart.getId());

        //then
        Assertions.assertEquals(0, cartResponseDtos.size());
    }
}