package capstonedesign.medicalproduct.service;

import capstonedesign.medicalproduct.dto.review.ReviewedItemDto;
import capstonedesign.medicalproduct.dto.review.Uploadedfile;
import capstonedesign.medicalproduct.domain.entity.Item;
import capstonedesign.medicalproduct.domain.entity.Member;
import capstonedesign.medicalproduct.domain.entity.Review;
import capstonedesign.medicalproduct.dto.review.ReviewResponseDto;
import capstonedesign.medicalproduct.exception.ItemNotFoundException;
import capstonedesign.medicalproduct.exception.MemberNotFoundException;
import capstonedesign.medicalproduct.exception.ReviewNotFoundException;
import capstonedesign.medicalproduct.repository.item.ItemRepository;
import capstonedesign.medicalproduct.repository.member.MemberRepository;
import capstonedesign.medicalproduct.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final ReviewRepository reviewRepository;
    @Transactional
    public long save(long memberId, long itemId, String title, String content, Uploadedfile uploadedfile) {

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException("해당 상품은 없습니다. id" + itemId));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("해당 회원은 없습니다. id" + itemId));

        Review review = Review.createReview(member, item, title, content, uploadedfile.getUploadFileName(), uploadedfile.getStoreFileName());

        return reviewRepository.save(review).getId();
    }

    public ReviewResponseDto findById(long reviewId) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("해당 후기는 없습니다. id = " + reviewId));

        return new ReviewResponseDto(review);
    }

    public List<ReviewedItemDto> findAllByMemberId(long memberId) {

        return reviewRepository.findAllByMemberId(memberId);
    }


    public List<ReviewedItemDto> findAllByItemId(long itemId) {

        return reviewRepository.findAllByItemId(itemId);
    }

    @Transactional
    public void delete(long reviewId) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("해당 후기는 없습니다. id = " + reviewId));

        reviewRepository.delete(review);
    }
}