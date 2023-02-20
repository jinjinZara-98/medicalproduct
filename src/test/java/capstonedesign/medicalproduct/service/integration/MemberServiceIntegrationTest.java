package capstonedesign.medicalproduct.service.integration;

import capstonedesign.medicalproduct.dto.member.FindIdDto;
import capstonedesign.medicalproduct.dto.member.MemberDetailDto;
import capstonedesign.medicalproduct.dto.member.MemberResponseDTO;
import capstonedesign.medicalproduct.factory.member.MemberFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberServiceIntegrationTest extends ServiceIntegrationTest{

    @Test
    @DisplayName("회원 가입을 진행한다.")
    public void save() {
        long savedMemberId = memberService.save(MemberFactory.makeMemberRequestDto());

        MemberDetailDto memberDetailDto = memberService.findById(savedMemberId);

        Assertions.assertEquals("임꺽정", memberDetailDto.getName());
    }

    @Test
    @DisplayName("회원 정보를 확인한다.")
    public void findById() {
        MemberDetailDto memberDetailDTO = memberService.findById(member.getId());

        Assertions.assertEquals(member.getLoginId(), memberDetailDTO.getLoginId());
    }

    @Test
    @DisplayName("중복 회원 검증을 진행한다.")
    public void existsByLoginId() {
        boolean result = memberService.existsByLoginId(member.getLoginId());

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("아이디 찾기에 사용할 회원 정보를 로드한다.")
    public void findByNameAndPhoneNumber() {
        FindIdDto findIdDto = MemberFactory.makeFindIdDto();

        MemberResponseDTO memberResponseDTO1 = memberService.findByNameAndPhoneNumber(findIdDto);

        Assertions.assertEquals("홍길동", memberResponseDTO1.getName());
    }

    @Test
    @DisplayName("비밀번호 찾기에 사용할 회원 정보를 로드한다.")
    public void findByLoginId() {
        MemberResponseDTO memberResponseDTO = memberService.findByLoginId(member.getLoginId());

        //then
        Assertions.assertEquals("홍길동", memberResponseDTO.getName());
    }

    @Test
    @DisplayName("변경된 회원 정보를 확인한다.")
    public void updateMemberInfo() {
        memberService.updateMemberInfo(member.getId(), MemberFactory.makeUpdateMemberDetailDTO());
        persistenceContextClear();

        MemberDetailDto memberResponseDTO = memberService.findById(member.getId());

        Assertions.assertEquals( "임꺽정", memberResponseDTO.getName());
    }

    private void persistenceContextClear() {
        em.flush();
        em.clear();
    }
}