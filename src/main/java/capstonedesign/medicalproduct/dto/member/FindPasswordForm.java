package capstonedesign.medicalproduct.dto.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class FindPasswordForm {

    @NotEmpty(message = "아이디 입력이 필수입니다.")
    private String loginId;
}
