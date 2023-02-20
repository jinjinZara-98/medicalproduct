package capstonedesign.medicalproduct.service;

import capstonedesign.medicalproduct.domain.entity.Cart;
import capstonedesign.medicalproduct.domain.entity.Item;
import capstonedesign.medicalproduct.domain.entity.Member;
import capstonedesign.medicalproduct.dto.cart.CartResponseDto;
import capstonedesign.medicalproduct.exception.CartNotFoundException;
import capstonedesign.medicalproduct.exception.ItemNotFoundException;
import capstonedesign.medicalproduct.exception.MemberNotFoundException;
import capstonedesign.medicalproduct.repository.cart.CartRepository;
import capstonedesign.medicalproduct.repository.item.ItemRepository;
import capstonedesign.medicalproduct.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartService {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    @Transactional
    public long save(long memberId, long itemId, int quantity) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("해당 사용자가 없습니다. id = " + memberId));

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException("해당 상품은 없습니다. id = " + itemId));

        Cart cart = Cart.createCart(member, item, quantity);

        return cartRepository.save(cart).getId();
    }

    public List<CartResponseDto> findAllByMemberId (Long memberId) {

        return cartRepository.findAllByMemberId(memberId);
    }

    @Transactional
    public void increaseQuantity(long cartId){
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("해당 장바구니 상품은 없습니다. id = " + cartId));

        cart.increaseQuantity();
    }

    @Transactional
    public void decreaseQuantity(long cartId){
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("해당 장바구니 상품은 없습니다. id = " + cartId));

        cart.decreaseQuantity();

        if(cart.getQuantity() <= 0) {
            cartRepository.delete(cart);
        }
    }

    @Transactional
    public void delete(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("해당 장바구니 상품은 없습니다. id = " + cartId));

        cartRepository.delete(cart);
    }
}