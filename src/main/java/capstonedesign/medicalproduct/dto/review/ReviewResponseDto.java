package capstonedesign.medicalproduct.dto.review;

import capstonedesign.medicalproduct.domain.entity.Review;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewResponseDto {
    private long id;
    private String title;
    private String content;
    private String uploadFileName;
    private String storeFileName;
    private LocalDateTime reviewDate;

    public ReviewResponseDto(Review review) {
        this.id = review.getId();
        this.title = review.getTitle();
        this.content = review.getContent();
        this.uploadFileName = review.getUploadFileName();
        this.storeFileName = review.getStoreFileName();
        this.reviewDate = review.getReviewDate();
    }
}
