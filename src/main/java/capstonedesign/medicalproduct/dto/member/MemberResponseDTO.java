package capstonedesign.medicalproduct.dto.member;

import capstonedesign.medicalproduct.domain.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDTO {
    private String loginId;
    private String name;
    private String email;

    public MemberResponseDTO(Member member) {
        this.loginId = member.getLoginId();
        this.name = member.getInformation().getName();
        this.email = member.getEmail();
    }
}
