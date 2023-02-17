package capstonedesign.medicalproduct.service.integration;

import capstonedesign.medicalproduct.dto.review.Uploadedfile;
import capstonedesign.medicalproduct.dto.review.ReviewDto;
import capstonedesign.medicalproduct.dto.review.ReviewResponseDto;
import capstonedesign.medicalproduct.factory.review.ReviewFactory;
import org.junit.jupiter.api.*;
import java.util.List;

class ReviewServiceIntegrationTest extends ServiceIntegrationTest{

    @Test
    @DisplayName("후기를 생성한다.")
    public void save(){

        //given
        Uploadedfile uploadedfile = ReviewFactory.makeUploadedFile();
        long reviewId = reviewService.save(member.getId(), item.getId(),"굿", "배송이 빠르네요", uploadedfile);

        //when
        ReviewResponseDto reviewResponseDto = reviewService.findById(reviewId);

        //then
        Assertions.assertEquals("배송이 빠르네요", reviewResponseDto.getContent());
    }

    @Test
    @DisplayName("회원이 작성한 후기 리스트를 가져온다.")
    public void findAllByMemberId(){

        //given

        //when
        List<ReviewDto> reviewDtoList = reviewService.findAllByMemberId(member.getId());

        //then
        Assertions.assertEquals(1, reviewDtoList.size());
    }

    @Test
    @DisplayName("상품에 대한 후기 리스트를 가져온다.")
    public void findAllByItemId(){

        //given

        //when
        List<ReviewDto> reviewDtoList = reviewService.findAllByItemId(item.getId());

        //then
        Assertions.assertEquals(1, reviewDtoList.size());
    }

    @Test
    @DisplayName("후기를 삭제한다.")
    public void delete(){

        //given
        reviewService.delete(testDB.findReview().getId());

        //when
        List<ReviewDto> reviewDtoList = reviewService.findAllByMemberId(member.getId());

        //then
        Assertions.assertEquals(0, reviewDtoList.size());
    }
}