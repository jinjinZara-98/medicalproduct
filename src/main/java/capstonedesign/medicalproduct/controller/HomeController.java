package capstonedesign.medicalproduct.controller;

import capstonedesign.medicalproduct.dto.item.ItemSearch;
import capstonedesign.medicalproduct.dto.item.ItemDto;
import capstonedesign.medicalproduct.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ItemService itemService;

    @GetMapping("/")
    public String home(@ModelAttribute("itemSearch") ItemSearch itemSearch, Model model,
                       @RequestParam(required = false, defaultValue = "0", value ="page") int page) {

        Page<ItemDto> listPage = itemService.findAllByName(itemSearch, page);

        List<ItemDto> result = listPage.getContent();
        int totalPage = listPage.getTotalPages();

        model.addAttribute("items",  result);
        model.addAttribute("totalPage", totalPage);

        return "home";
    }
}