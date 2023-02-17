package capstonedesign.medicalproduct.dto.review;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class ReviewRegisterForm {

    private long memberId;

    private long reviewId;

    private long itemId;

    private String itemName;

    private String itemImageSrc;

    @NotEmpty(message = "후기 제목을 입력해주세요")
    private String title;

    @NotEmpty(message = "후기 내용을 입력해주세요")
    private String content;

    private MultipartFile imageFile;

    private String uploadFileName;

    private String storeFileName;

    @QueryProjection
    public ReviewRegisterForm(long reviewId, long itemId, String itemName, String itemImageSrc,
                              String title, String content, String uploadFileName, String storeFileName) {
        this.reviewId = reviewId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemImageSrc = itemImageSrc;
        this.title = title;
        this.content = content;
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
