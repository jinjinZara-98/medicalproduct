package capstonedesign.medicalproduct.dto.member;

import capstonedesign.medicalproduct.domain.entity.Member;
import capstonedesign.medicalproduct.security.member.MemberInfo;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class MemberDetailDTO {

    @NotEmpty(message = "아이디는 필수입니다")
    private String loginId;

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

    public MemberDetailDTO(MemberInfo memberInfo) {
        this.loginId = memberInfo.getLoginId();
        this.name = memberInfo.getName();
        this.phoneNumber = memberInfo.getPhoneNumber();
        this.address = memberInfo.getAddress();
        this.addressDetail = memberInfo.getAddressDetail();
        this.email = memberInfo.getEmail();
        this.accountHost = memberInfo.getAccountHost();
        this.bankName = memberInfo.getBankName();
        this.accountNumber = memberInfo.getAccountNumber();
    }

    public MemberDetailDTO(Member member) {
        this.loginId = member.getLoginId();
        this.name = member.getInformation().getName();
        this.phoneNumber = member.getInformation().getPhoneNumber();
        this.address = member.getInformation().getAddress();
        this.addressDetail = member.getInformation().getAddressDetail();
        this.email = member.getEmail();
        this.accountHost = member.getInformation().getAccountHost();
        this.bankName = member.getInformation().getBankName();
        this.accountNumber = member.getInformation().getAccountNumber();
    }

    @Builder
    public MemberDetailDTO(String name, String phoneNumber, String address, String addressDetail,
                           String email, String accountHost, String bankName, String accountNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.addressDetail = addressDetail;
        this.email = email;
        this.accountHost = accountHost;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
    }
}