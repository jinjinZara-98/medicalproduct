package capstonedesign.medicalproduct.dto.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginFormDto {

    @NotEmpty(message = "ID는 필수 입니다")
    private String loginId;

    @NotEmpty(message = "비밀번호는 필수 입니다")
    private String password;
}
