package capstonedesign.medicalproduct.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Information {

    private String name;
    private String phoneNumber;
    private String address;
    private String addressDetail;
    private String accountHost;
    private String bankName;
    private String accountNumber;

    public Information(String name, String phoneNumber, String address, String addressDetail,
                       String accountHost, String bankName, String accountNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.addressDetail = addressDetail;
        this.accountHost = accountHost;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
    }
}
