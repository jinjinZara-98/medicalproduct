package capstonedesign.medicalproduct.controller;

import capstonedesign.medicalproduct.dto.review.Uploadedfile;
import capstonedesign.medicalproduct.dto.item.ItemDetailDto;
import capstonedesign.medicalproduct.dto.review.ReviewResponseDto;
import capstonedesign.medicalproduct.dto.review.ReviewDto;
import capstonedesign.medicalproduct.dto.review.ReviewRegisterForm;
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
@Slf4j
@RequiredArgsConstructor
public class ReviewController {

    private final ItemService itemService;
    private final ReviewService reviewService;
    private final AwsS3Service awsS3Service;

    @GetMapping("review/item/{id}")
    public String register(@PathVariable("id") long itemId, Model model){

        ItemDetailDto item = itemService.findById(itemId);

        ReviewRegisterForm reviewRegisterForm = new ReviewRegisterForm();

        reviewRegisterForm.setItemId(item.getId()); reviewRegisterForm.setItemName(item.getName());
        reviewRegisterForm.setItemImageSrc(item.getImageSrc());

        model.addAttribute("reviewRegisterForm", reviewRegisterForm);

        return "reviews/reviewRegister";
    }

    @PostMapping("review/register")
    public String registerPro(@AuthenticationPrincipal MemberInfo member,
                              @Valid @ModelAttribute("reviewRegisterForm") ReviewRegisterForm reviewRegisterForm,
                              BindingResult bindingResult) throws IOException {

        if(bindingResult.hasErrors()) {

            return "reviews/reviewRegister";
        }

        if(reviewRegisterForm.getImageFile().isEmpty()) {

            bindingResult.reject("imageFileIsNull", "후기 사진을 첨부해주세요!");

            return "reviews/reviewRegister";
        }

        Uploadedfile uploadedfile = awsS3Service.uploadFile(reviewRegisterForm.getImageFile());

        reviewService.save(member.getId(), reviewRegisterForm.getItemId(),
                reviewRegisterForm.getTitle(), reviewRegisterForm.getContent(), uploadedfile);

        return "redirect:/";
    }

    @GetMapping("review/reviewList")
    public String getReviewList(@AuthenticationPrincipal MemberInfo member, Model model) {

        List<ReviewDto> reviewList = reviewService.findAllByMemberId(member.getId());

        List<ReviewDto> newReviewList = new ArrayList<>();
        for (ReviewDto reviewDto : reviewList) {
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
    public String deleteReview(@PathVariable("id") long reviewId) {

        ReviewResponseDto reviewResponseDto = reviewService.findById(reviewId);
        reviewService.delete(reviewId);
        awsS3Service.deleteFile(reviewResponseDto.getStoreFileName());

        return "redirect:/review/reviewList";
    }
}