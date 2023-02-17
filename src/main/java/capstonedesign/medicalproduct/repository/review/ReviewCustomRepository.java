package capstonedesign.medicalproduct.repository.review;

import capstonedesign.medicalproduct.dto.review.ReviewDto;

import java.util.List;

public interface ReviewCustomRepository {
    List<ReviewDto> findAllByMemberId(long memberId);
    List<ReviewDto> findAllByItemId(long itemId);
}
