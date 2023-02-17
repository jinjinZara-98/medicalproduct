package capstonedesign.medicalproduct.security.member;

import capstonedesign.medicalproduct.domain.MemberRole;
import capstonedesign.medicalproduct.domain.entity.Member;
import capstonedesign.medicalproduct.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service("userDetailsService")
@Transactional
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findByLoginId(username)
                .orElseThrow(() -> new UsernameNotFoundException("UsernameNotFoundException"));


        List<GrantedAuthority> rules = new ArrayList<>();

        rules.add(new SimpleGrantedAuthority("ROLE_MEMBER"));

        if(member.getMemberRole() == MemberRole.ROLE_ADMIN) {
            rules.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        MemberInfo memberInfo = MemberInfo.builder()
                .id(member.getId())
                .loginId(member.getLoginId())
                .password(member.getPassword())
                .name(member.getInformation().getName())
                .phoneNumber(member.getInformation().getPhoneNumber())
                .address(member.getInformation().getAddress())
                .addressDetail(member.getInformation().getAddressDetail())
                .accountHost(member.getInformation().getAccountHost())
                .bankName(member.getInformation().getBankName())
                .accountNumber(member.getInformation().getAccountNumber())
                .email(member.getEmail())
                .build();

        return new MemberContext(memberInfo, rules);
    }
}