package capstonedesign.medicalproduct.dto.review;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class ReviewRequestDto {

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
}
