package capstonedesign.medicalproduct.security.member;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.Collection;

@Getter
public class MemberContext extends User {

    MemberInfo memberInfo;

    public MemberContext(MemberInfo memberInfo, Collection<? extends GrantedAuthority> authorities) {
        super(memberInfo.getLoginId(), memberInfo.getPassword(), authorities);
        this.memberInfo = memberInfo;
    }
}