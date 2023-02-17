package capstonedesign.medicalproduct.dto.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class NewPasswordDto {

    private String loginId;

    @NotEmpty(message = "새로운 비밀번호를 입력해주세요")
    private String newPassword;

    @NotEmpty(message = "새로운 비밀번호를 다시 입력해주세요")
    private String newPassword2;
}
