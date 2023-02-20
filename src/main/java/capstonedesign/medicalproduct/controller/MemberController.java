package capstonedesign.medicalproduct.controller;

import capstonedesign.medicalproduct.dto.member.*;
import capstonedesign.medicalproduct.repository.member.MemberRepository;
import capstonedesign.medicalproduct.security.member.MemberInfo;
import capstonedesign.medicalproduct.service.EmailService;
import capstonedesign.medicalproduct.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final PasswordEncoder passwordEncoder;
    private final MemberService memberService;
    private final EmailService emailService;

    @GetMapping("members/register")
    public String join(@ModelAttribute("MemberRegisterForm") MemberRequestDto form){

        return "members/register";
    }

    @PostMapping("members/register")
    public String joinPro(@Valid @ModelAttribute("MemberRegisterForm") MemberRequestDto form,
                          BindingResult bindingResult){

        if (memberService.existsByLoginId(form.getLoginId()))
            bindingResult.reject("DuplicateLoginId", "입력하신 아이디는 다른 회원이 사용중입니다");

        if (bindingResult.hasErrors()) {

            return "members/register";
        }

        form.setPassword(passwordEncoder.encode(form.getPassword()));

        memberService.save(form);

        return "redirect:/login";
    }

    @GetMapping("members/idFind")
    public String findId(@ModelAttribute("idFindForm") FindIdDto findIdDto) {

        return "members/idFind";
    }

    @PostMapping("members/idFind")
    public String findIdPro(@Valid @ModelAttribute("idFindForm") FindIdDto findIdDto,
                            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            return "members/idFind";
        }

        MemberResponseDTO member = null;

        String loginId = null;

        try {
            member = memberService.findByNameAndPhoneNumber(findIdDto);
            loginId = member.getLoginId();
            model.addAttribute("result", loginId);
        } catch (IllegalArgumentException e) {
            model.addAttribute("result", loginId);
        }

        return "members/idFindResult";
    }

    @GetMapping("members/passwordFind")
    public String findPassword(@ModelAttribute("passwordFindForm") FindPasswordFormDto findPasswordFormDto) {

        return "members/passwordFind";
    }

    @PostMapping("members/passwordFind")
    public String findPasswordPro(@Valid @ModelAttribute("passwordFindForm")
                                  FindPasswordFormDto findPasswordForm, BindingResult bindingResult,
                                  Model model) {

        if(bindingResult.hasErrors()) {
            return "members/passwordFind";
        }

        MemberResponseDTO member = null;

        try {
            member = memberService.findByLoginId(findPasswordForm.getLoginId());
        } catch (IllegalArgumentException e) {
            model.addAttribute("result", member);
            return "members/passwordFindResult";
        }

        model.addAttribute("result", member);

        return "members/passwordFindResult";
    }

    @PostMapping("members/sendmail")
    public String sendMail(MemberResponseDTO memberResponseDTO) {

        emailService.sendPassword(memberResponseDTO);

        return "redirect:/login";
    }

    @GetMapping("/members/find/password/change")
    public String createPassword(@ModelAttribute("newPasswordDto") NewPasswordDto newPasswordDto) {

        return "members/newPassword";
    }

    @PostMapping("/members/find/password/change")
    public String createPasswordPro(@Valid @ModelAttribute("newPasswordDto") NewPasswordDto newPasswordDto,
                                 BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "members/newPassword";
        }

        if(!newPasswordDto.getNewPassword().equals(newPasswordDto.getNewPassword2())) {
            bindingResult.reject("newPasswordWrong", "신규 비밀번호와 재입력한 신규 비밀번호가 같지 않습니다");

            return "members/newPassword";
        }

        memberService.updatePassword(newPasswordDto.getLoginId(),
                passwordEncoder.encode(newPasswordDto.getNewPassword()));

        return "redirect:/login";
    }

    @GetMapping("members/memberDetail")
    public String memberDetail(@AuthenticationPrincipal MemberInfo member, Model model) {

        MemberDetailDto memberDetail = new MemberDetailDto(member);

        model.addAttribute("memberDetail", memberDetail);

        return "members/myPage";
    }

    @PostMapping("members/update")
    public String updateMemberInfo(HttpServletRequest request, HttpServletResponse response,
                                   @AuthenticationPrincipal MemberInfo member,
                                   @Valid @ModelAttribute("memberDetail") MemberDetailDto memberDetailDTO,
                                   BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {

            return "members/myPage";
        }

        memberService.updateMemberInfo(member.getId(), memberDetailDTO);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        new SecurityContextLogoutHandler().logout(request, response, authentication);

        return "redirect:/";
    }

    @GetMapping("member/password/update")
    public String updatePassword(@ModelAttribute("passwordUpdateDto") PasswordUpdateDto passwordUpdateDto) {

        return "members/passwordUpdate";
    }

    @PostMapping("member/password/update")
    public String updatePasswordPro(@AuthenticationPrincipal MemberInfo member,
                                    @Valid @ModelAttribute("passwordUpdateDto") PasswordUpdateDto passwordUpdateDto,
                                    BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {

            return "members/passwordUpdate";
        } else if (!passwordEncoder.matches(passwordUpdateDto.getPassword(), member.getPassword())) {

            bindingResult.reject("presentPasswordWrong", "입력하신 현재 비밀번호가 틀립니다");

            return "members/passwordUpdate";
        } else if(!passwordUpdateDto.getNewPassword().equals(passwordUpdateDto.getNewPassword2())) {

            bindingResult.reject("newPasswordWrong", "신규 비밀번호와 재입력한 신규 비밀번호가 같지 않습니다");

            return "members/passwordUpdate";
        }

        memberService.updatePassword(member.getLoginId(), passwordEncoder.encode(passwordUpdateDto.getNewPassword()));

        return "redirect:/";
    }
}