package capstonedesign.medicalproduct.domain.entity;

import capstonedesign.medicalproduct.domain.Information;
import capstonedesign.medicalproduct.domain.MemberRole;
import capstonedesign.medicalproduct.dto.member.MemberDetailDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Embedded
    private Information information;

    @Column
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberRole memberRole;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Cart> carts= new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Review> reviews= new ArrayList<>();

    @Builder
    public Member(String loginId, String password, Information information, String email, MemberRole memberRole) {
        this.loginId = loginId;
        this.password = password;
        this.information = information;
        this.email = email;
        this.memberRole = memberRole;
    }

    public Member update(MemberDetailDto memberDetailDTO) {
        this.information.setName(memberDetailDTO.getName());
        this.information.setPhoneNumber(memberDetailDTO.getPhoneNumber());
        this.information.setAddress(memberDetailDTO.getAddress());
        this.information.setAddressDetail(memberDetailDTO.getAddressDetail());
        this.email = memberDetailDTO.getEmail();
        this.information.setAccountHost(memberDetailDTO.getAccountHost());
        this.information.setBankName(memberDetailDTO.getBankName());
        this.information.setAccountNumber(memberDetailDTO.getAccountNumber());

        return this;
    }

    public String updatePassword(String password) {
        this.password = password;

        return this.password;
    }
}
