package capstonedesign.medicalproduct.factory.review;

import capstonedesign.medicalproduct.dto.review.ReviewedItemDto;
import capstonedesign.medicalproduct.dto.review.Uploadedfile;
import capstonedesign.medicalproduct.factory.BasicFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReviewFactory extends BasicFactory {

    public static Uploadedfile makeUploadedFile() {
        return new Uploadedfile("9780b5f-2f60-4aca-954b-c5b6d8f3206a.jfif", "images.png");
    }

    public static List<ReviewedItemDto> makeReviewDtoList() {
        ReviewedItemDto reviewDto = new ReviewedItemDto(1L, "홍길동", 1L, "수술가운", "http://www.yolomarket.kr/data/item/G171103/thumb-4A06002040_L_250x250.jpg"
                                            , LocalDateTime.now(), "좋아요", "잘 받았습니다.", "9780b5f-2f60-4aca-954b-c5b6d8f3206a.jfif", "images.png");

        List<ReviewedItemDto> reviewDtoList =new ArrayList<>();
        reviewDtoList.add(reviewDto);

        return reviewDtoList;
    }
}
