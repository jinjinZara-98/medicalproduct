package capstonedesign.medicalproduct.dto.review;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReviewedItemDto {

    private long reviewId;

    private String memberName;

    private long itemId;

    private String itemName;

    private String itemImageSrc;

    @DateTimeFormat(pattern = "yyyy년 MM월 dd일 HH시 mm분")
    private LocalDateTime reviewDate;

    private String title;

    private String content;

    private String uploadFileName;

    private String storeFileName;

    @QueryProjection
    public ReviewedItemDto(long reviewId, String memberName, String title, String content,
                     LocalDateTime reviewDate, String uploadFileName, String storeFileName) {
        this.reviewId = reviewId;
        this.memberName = memberName;
        this.title = title;
        this.content = content;
        this.reviewDate = reviewDate;
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }

    @QueryProjection
    public ReviewedItemDto(long reviewId, long itemId, String itemName, String itemImageSrc, LocalDateTime reviewDate,
                     String title, String content, String uploadFileName, String storeFileName) {
        this.reviewId = reviewId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemImageSrc = itemImageSrc;
        this.reviewDate = reviewDate;
        this.title = title;
        this.content = content;
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
