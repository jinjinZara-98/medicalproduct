package capstonedesign.medicalproduct.repository;

import capstonedesign.medicalproduct.domain.MemberRole;
import capstonedesign.medicalproduct.domain.entity.*;
import capstonedesign.medicalproduct.dto.review.ReviewedItemDto;
import capstonedesign.medicalproduct.factory.member.MemberFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;


public class ReviewRepositoryTest extends RepositoryTest{

    Member member;
    Item item;
    Review review;

    @BeforeEach
    void beforeEach() {
        member = memberRepository.save(new Member("gildong123", "jin", MemberFactory.makeInformation(), "gildong123@naver.com",
                MemberRole.ROLE_USER));

        item = itemRepository.save(new Item("의료소모품", "http://www.yolomarket.kr/data/item/G171103/thumb-4A06002040_L_250x250.jpg",
                "수술시 시술자가 입는 입는 내의입니다. 린넨 소재이므로 세탁 후 재사용이 가능합니다.", "수술가운", 20000));

        review = reviewRepository.save(new Review("좋아요", "잘 받았습니다.", member, item,
                "8780b5f-2f60-4aca-954b-c5b6d8f3206a.jfif", "images.png", LocalDateTime.now()));
    }

    @Test
    @DisplayName("회원이 작성한 후기 리스트를 가져온다.")
    public void findAllByMemberId() {

        //given

        //when
        List<ReviewedItemDto> reviewDtoList = reviewRepository.findAllByMemberId(member.getId());

        //then
        Assertions.assertEquals("잘 받았습니다.", reviewDtoList.get(0).getContent());
    }

    @Test
    @DisplayName("상품에 대한 후기 리스트를 가져온다.")
    public void findAllByItemId() {

        //given

        //when
        List<ReviewedItemDto> ActualResult =  reviewRepository.findAllByItemId(item.getId());

        //then
        Assertions.assertEquals("잘 받았습니다.", ActualResult.get(0).getContent());
    }
}
