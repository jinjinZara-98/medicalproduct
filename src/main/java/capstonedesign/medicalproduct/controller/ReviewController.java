package capstonedesign.medicalproduct.controller;

import capstonedesign.medicalproduct.dto.review.ReviewRequestDto;
import capstonedesign.medicalproduct.dto.review.ReviewedItemDto;
import capstonedesign.medicalproduct.dto.review.Uploadedfile;
import capstonedesign.medicalproduct.dto.item.ItemDetailDto;
import capstonedesign.medicalproduct.dto.review.ReviewResponseDto;
import capstonedesign.medicalproduct.security.member.MemberInfo;
import capstonedesign.medicalproduct.service.AwsS3Service;
import capstonedesign.medicalproduct.service.ItemService;
import capstonedesign.medicalproduct.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ItemService itemService;
    private final ReviewService reviewService;
    private final AwsS3Service awsS3Service;

    @GetMapping("review/item/{id}")
    public String register(@PathVariable("id") long itemId, Model model){

        ItemDetailDto itemDetailDto = itemService.findById(itemId);

        ReviewRequestDto reviewRequestDto = new ReviewRequestDto(itemDetailDto);

        model.addAttribute("reviewRequestDto", reviewRequestDto);

        return "reviews/reviewRegister";
    }

    @PostMapping("review/register")
    public String registerPro(@AuthenticationPrincipal MemberInfo member,
                              @Valid @ModelAttribute("reviewRequestDto") ReviewRequestDto reviewRequestDto,
                              BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {

            return "reviews/reviewRegister";
        }

        if(reviewRequestDto.getImageFile().isEmpty()) {

            bindingResult.reject("imageFileIsNull", "후기 사진을 첨부해주세요!");

            return "reviews/reviewRegister";
        }

        Uploadedfile uploadedfile = awsS3Service.uploadFile(reviewRequestDto.getImageFile());

        reviewService.save(member.getId(), reviewRequestDto.getItemId(),
                reviewRequestDto.getTitle(), reviewRequestDto.getContent(), uploadedfile);

        return "redirect:/";
    }

    @GetMapping("review/reviewList")
    public String findAllByMemberId(@AuthenticationPrincipal MemberInfo member, Model model) {

        List<ReviewedItemDto> reviewList = reviewService.findAllByMemberId(member.getId());

        List<ReviewedItemDto> newReviewList = new ArrayList<>();
        for (ReviewedItemDto reviewDto : reviewList) {
            reviewDto.setStoreFileName(awsS3Service.getThumbnailPath(reviewDto.getStoreFileName()));
            newReviewList.add(reviewDto);
        }

        model.addAttribute("reviewList", reviewList);

        return "reviews/reviewList";
    }

    @GetMapping("/attach/{reviewId}")
    public ResponseEntity<byte[]> downloadReviewImage(@PathVariable Long reviewId) throws IOException {

        ReviewResponseDto review = reviewService.findById(reviewId);

        String storeFileName = review.getStoreFileName();

        String uploadFileName = review.getUploadFileName();

        return awsS3Service.getObject(storeFileName, uploadFileName);
    }

    @PostMapping("/review/{id}/cancel")
    public String delete(@PathVariable("id") long reviewId) {

        ReviewResponseDto reviewResponseDto = reviewService.findById(reviewId);
        reviewService.delete(reviewId);
        awsS3Service.deleteFile(reviewResponseDto.getStoreFileName());

        return "redirect:/review/reviewList";
    }
}