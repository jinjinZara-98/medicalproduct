package capstonedesign.medicalproduct.controller;

import capstonedesign.medicalproduct.dto.item.ItemSearch;
import capstonedesign.medicalproduct.dto.item.ItemDetailDto;
import capstonedesign.medicalproduct.dto.item.ItemDto;
import capstonedesign.medicalproduct.dto.review.ReviewedItemDto;
import capstonedesign.medicalproduct.service.AwsS3Service;
import capstonedesign.medicalproduct.service.ItemService;
import capstonedesign.medicalproduct.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ItemController {

    private final AwsS3Service awsS3Service;
    private final ItemService itemService;
    private final ReviewService reviewService;

    @GetMapping("item/{itemId}")
    public String itemDetail(@PathVariable("itemId") Long itemId,
                             @ModelAttribute("itemSearch") ItemSearch itemSearch, Model model){

        ItemDetailDto itemDetailDto = itemService.findById(itemId);

        List<ReviewedItemDto> reviewList = reviewService.findAllByItemId(itemId);

        List<ReviewedItemDto> newReviewList = new ArrayList<>();

        for (ReviewedItemDto reviewDto : reviewList) {
            reviewDto.setStoreFileName(awsS3Service.getThumbnailPath(reviewDto.getStoreFileName()));
            newReviewList.add(reviewDto);
        }

        model.addAttribute("itemDetailDto", itemDetailDto);
        model.addAttribute("reviewList", reviewList);

        return "item/itemDetail";
    }

    @GetMapping("items/{category}")
    public String categorySearch(@PathVariable("category") String category,
                                 @ModelAttribute("itemSearch") ItemSearch itemSearch, Model model,
                                 @RequestParam(required = false, defaultValue = "0", value ="page") int page){

        Page<ItemDto> listPage = itemService.findAllByCategory(category, page);

        int totalPage = listPage.getTotalPages();

        model.addAttribute("items",  listPage.getContent());

        model.addAttribute("totalPage", totalPage);

        return "home";
    }
}