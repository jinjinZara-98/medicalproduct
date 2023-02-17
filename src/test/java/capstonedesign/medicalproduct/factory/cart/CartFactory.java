package capstonedesign.medicalproduct.factory.cart;

import capstonedesign.medicalproduct.dto.cart.CartResponseDto;
import capstonedesign.medicalproduct.factory.BasicFactory;

import java.util.ArrayList;
import java.util.List;

public class CartFactory extends BasicFactory {

    public static List<CartResponseDto> makeCartResponseDto() {

        List<CartResponseDto> cartResponseDtos = new ArrayList<>();
        cartResponseDtos.add(new CartResponseDto(1L, 1L, "수술가운",
                "http://www.yolomarket.kr/data/item/G171103/thumb-4A06002040_L_250x250.jpg", 20000, 2));

        return cartResponseDtos;
    }
}
