package capstonedesign.medicalproduct.controller;

import capstonedesign.medicalproduct.security.member.MemberInfo;
import capstonedesign.medicalproduct.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception, Model model){

        model.addAttribute("error",error);
        model.addAttribute("exception",exception);

        return "login/loginForm";
    }

    @GetMapping("/denied")
    public String accessDenied(@AuthenticationPrincipal MemberInfo memberInfo, Model model){

        model.addAttribute("memberName",memberInfo.getName());

        return "login/denied";
    }
}