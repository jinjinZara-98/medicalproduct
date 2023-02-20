package capstonedesign.medicalproduct.dto.member;

import capstonedesign.medicalproduct.domain.Information;
import capstonedesign.medicalproduct.domain.MemberRole;
import capstonedesign.medicalproduct.domain.entity.Member;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class MemberRequestDto {

    @NotEmpty(message = "아이디는 필수입니다")
    private String loginId;

    @NotEmpty(message = "비밀번호는 필수입니다")
    private String password;

    @NotEmpty(message = "이름은 필수입니다")
    private String name;

    @NotEmpty(message = "전화번호 필수입니다")
    @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "올바른 휴대폰 번호를 입력해주세요.")
    private String phoneNumber;

    @NotEmpty(message = "주소는 필수입니다")
    private String address;

    @NotEmpty(message = "상세주소는 필수입니다")
    private String addressDetail;

    @NotEmpty(message = "이메일은 필수입니다")
    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    private String email;

    @NotEmpty(message = "예금주명은 필수입니다")
    private String accountHost;

    @NotEmpty(message = "은행명은 필수입니다")
    private String bankName;

    @NotEmpty(message = "계좌번호는 필수입니다")
    private String accountNumber;

    @Builder
    public MemberRequestDto(String loginId, String password, String name, String phoneNumber, String address,
                            String addressDetail, String email, String accountHost, String bankName, String accountNumber) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.addressDetail = addressDetail;
        this.email = email;
        this.accountHost = accountHost;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
    }

    public Member toEntity() {

        return Member.builder()
                .loginId(loginId)
                .password(password)
                .information(new Information(name, phoneNumber, address, addressDetail, accountHost, bankName, accountNumber))
                .email(email)
                .memberRole(MemberRole.ROLE_USER)
                .build();
    }
}