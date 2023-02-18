package capstonedesign.medicalproduct.repository;

import capstonedesign.medicalproduct.domain.MemberRole;
import capstonedesign.medicalproduct.domain.entity.Member;
import capstonedesign.medicalproduct.factory.member.MemberFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberRepositoryTest extends RepositoryTest{

    Member member;

    @BeforeEach
    void beforeEach() {
        member = memberRepository.save(new Member("gildong123", "jin", MemberFactory.makeInformation(), "gildong123@naver.com",
                MemberRole.ROLE_USER));
    }

    @Test
    @DisplayName("회원 정보를 확인한다.")
    public void findById() {

        //given

        //when
        Member member = memberRepository.findById(this.member.getId()).orElseThrow(IllegalArgumentException::new);

        //then
        Assertions.assertEquals("gildong123", member.getLoginId());
    }

    @Test
    @DisplayName("중복 회원 검증을 진행한다.")
    public void existsByLoginId() {

        //given

        //when
        boolean result = memberRepository.existsByLoginId(member.getLoginId());

        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("아이디 찾기에 사용할 회원 정보를 로드한다.")
    public void findByNameAndPhoneNumber() {

        //given

        //when
        Member member = memberRepository.findByNameAndPhoneNumber("홍길동", "01012341234")
                .orElseThrow(IllegalArgumentException::new);

        //then
        Assertions.assertEquals("홍길동", member.getInformation().getName());
    }

    @Test
    @DisplayName("비밀번호 찾기에 사용할 회원 정보를 로드한다.")
    public void findByLoginId() throws Exception{
        //given

        //when
        Member member = memberRepository.findByLoginId("gildong123").orElseThrow(IllegalArgumentException::new);

        //then
        Assertions.assertEquals("홍길동", member.getInformation().getName());
    }
}
