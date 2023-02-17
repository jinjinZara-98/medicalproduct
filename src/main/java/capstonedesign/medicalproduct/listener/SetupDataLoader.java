package capstonedesign.medicalproduct.listener;

import capstonedesign.medicalproduct.domain.Information;
import capstonedesign.medicalproduct.domain.MemberRole;
import capstonedesign.medicalproduct.domain.entity.Member;
import capstonedesign.medicalproduct.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if(alreadySetup) {
            return;
        }

       createAdmin();
       alreadySetup = true;
    }

    private void createAdmin() {
        boolean result = memberRepository.existsByLoginId("admin");

        if(!result) {
            Information information = new Information("관리자", "01012341234", "충북 충주시 대소원면 대학로 50",
                    "대원생활관", "관리자", "농협", "3520459732493");

            Member member = Member.builder()
                    .loginId("admin")
                    .password(passwordEncoder.encode("admin7340"))
                    .information(information)
                    .email("heojin0620@naver.com")
                    .memberRole(MemberRole.ROLE_ADMIN)
                    .build();

            memberRepository.save(member);
        }
    }
}
