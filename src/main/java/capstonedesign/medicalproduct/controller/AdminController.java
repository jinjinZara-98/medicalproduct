package capstonedesign.medicalproduct.controller;

import capstonedesign.medicalproduct.dto.member.MemberDetailDto;
import capstonedesign.medicalproduct.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final MemberService memberService;

    @GetMapping("/admin")
    public String admin(Model model) {
        List<MemberDetailDto> memberDetailDTOS = memberService.findAll();

        model.addAttribute("memberList", memberDetailDTOS);

        return "admin/memberList";
    }
}
