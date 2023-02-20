package capstonedesign.medicalproduct.repository.review;

import capstonedesign.medicalproduct.dto.review.ReviewedItemDto;

import java.util.List;

public interface ReviewCustomRepository {
    List<ReviewedItemDto> findAllByMemberId(long memberId);
    List<ReviewedItemDto> findAllByItemId(long itemId);
}
