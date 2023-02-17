package capstonedesign.medicalproduct.repository.cart;

import capstonedesign.medicalproduct.dto.cart.CartResponseDto;

import java.util.List;

public interface CartCustomRepository {
    List<CartResponseDto> findAllByMemberId(Long memberId);
}
