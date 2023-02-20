package capstonedesign.medicalproduct.service.unit;

import capstonedesign.medicalproduct.dto.review.ReviewedItemDto;
import capstonedesign.medicalproduct.dto.review.Uploadedfile;
import capstonedesign.medicalproduct.domain.entity.*;
import capstonedesign.medicalproduct.factory.item.ItemFactory;
import capstonedesign.medicalproduct.factory.member.MemberFactory;
import capstonedesign.medicalproduct.factory.review.ReviewFactory;
import capstonedesign.medicalproduct.repository.item.ItemRepository;
import capstonedesign.medicalproduct.repository.member.MemberRepository;
import capstonedesign.medicalproduct.repository.review.ReviewRepository;
import capstonedesign.medicalproduct.service.ReviewService;
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
public class ReviewServiceUnitTest {

    Member member;
    Item item;
    Review review;

    @InjectMocks
    ReviewService reviewService;

    @Mock
    ItemRepository itemRepository;

    @Mock
    MemberRepository memberRepository;

    @Mock
    ReviewRepository reviewRepository;

    @BeforeEach
    public void init() {
        member = MemberFactory.makeTestMember();
        item = ItemFactory.makeTestItem();
        review = ReviewFactory.makeTestReview(member, item);
    }

    @Test
    @DisplayName("후기를 생성한다.")
    public void save() {

        //given
        Uploadedfile uploadedfile = ReviewFactory.makeUploadedFile();
        Review createdReview = ReviewFactory.makeTestReview2(member, item);

        //mocking
        given(memberRepository.findById(member.getId())).willReturn(Optional.ofNullable(member));
        given(itemRepository.findById(item.getId())).willReturn(Optional.ofNullable(item));
        given(reviewRepository.save(any())).willReturn(createdReview);

        //when
        long reviewId = reviewService.save(member.getId(), item.getId(), "배송이 빨라요", "잘 쓰겠습니다.", uploadedfile);

        //then
        Assertions.assertEquals(createdReview.getId(), reviewId);
    }

    @Test
    @DisplayName("회원이 작성한 후기 리스트를 가져온다.")
    public void findAllByMemberId() {

        //given
        List<ReviewedItemDto> ExpectResult = ReviewFactory.makeReviewDtoList();

        //mocking
        given(reviewRepository.findAllByMemberId(member.getId())).willReturn(ExpectResult);

        //when
        List<ReviewedItemDto> ActualResult = reviewService.findAllByMemberId(member.getId());

        //then
        Assertions.assertEquals(ExpectResult.get(0).getContent(), ActualResult.get(0).getContent());
    }

    @Test
    @DisplayName("상품에 대한 후기 리스트를 가져온다.")
    public void findAllByItemId() {

        //given
        List<ReviewedItemDto> ExpectResult = ReviewFactory.makeReviewDtoList();

        //mocking
        given(reviewRepository.findAllByItemId(item.getId())).willReturn(ExpectResult);

        //when
        List<ReviewedItemDto> ActualResult = reviewService.findAllByItemId(member.getId());

        //then
        Assertions.assertEquals(ExpectResult.get(0).getContent(), ActualResult.get(0).getContent());
    }

    @Test
    @DisplayName("후기를 삭제한다.")
    public void delete() {

        //given

        //mocking
        given(reviewRepository.findById(review.getId())).willReturn(Optional.ofNullable(review));
        willDoNothing().given(reviewRepository).delete(review);

        //when
        reviewService.delete(review.getId());

        //then
        verify(reviewRepository, times(1)).findById(review.getId());
        verify(reviewRepository, times(1)).delete(review);
    }
}
