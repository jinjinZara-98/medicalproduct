package capstonedesign.medicalproduct.security.member;

import lombok.Builder;
import lombok.Data;

@Data
public class MemberInfo {

    private long id;
    private String loginId;
    private String password;
    private String name;
    private String phoneNumber;
    private String address;
    private String addressDetail;
    private String accountHost;
    private String bankName;
    private String accountNumber;
    private String email;

    @Builder
    public MemberInfo(long id, String loginId, String password, String name, String phoneNumber, String address,
                         String addressDetail, String accountHost, String bankName, String accountNumber, String email) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.addressDetail = addressDetail;
        this.accountHost = accountHost;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.email = email;
    }
}