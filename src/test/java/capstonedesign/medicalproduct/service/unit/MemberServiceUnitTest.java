package capstonedesign.medicalproduct.service.unit;

import capstonedesign.medicalproduct.domain.entity.Member;
import capstonedesign.medicalproduct.dto.member.FindIdDto;
import capstonedesign.medicalproduct.dto.member.MemberDetailDTO;
import capstonedesign.medicalproduct.dto.member.MemberRequestDto;
import capstonedesign.medicalproduct.dto.member.MemberResponseDTO;
import capstonedesign.medicalproduct.factory.member.MemberFactory;
import capstonedesign.medicalproduct.repository.member.MemberRepository;
import capstonedesign.medicalproduct.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class MemberServiceUnitTest {

    Member member;

    @InjectMocks
    MemberService memberService;

    @Mock
    MemberRepository memberRepository;

    @BeforeEach
    public void init() {
        member = MemberFactory.makeTestMember();
    }

    @Test
    @DisplayName("회원 가입을 진행한다.")
    public void save() {
        //given
        MemberRequestDto ExpectResult = MemberFactory.makeMemberRequestDto();
        Member joinedMember = MemberFactory.makeMemberRequestDto().toEntity();

        //mocking
        given(memberRepository.save(any())).willReturn(joinedMember);

        //when
        MemberDetailDTO ActualResult = memberService.save(ExpectResult);

        //then
        Assertions.assertEquals(ExpectResult.getName(), ActualResult.getName());
    }

    @Test
    @DisplayName("회원 정보를 확인한다.")
    public void findById() {

        //given

        //mocking
        given(memberRepository.findById(member.getId())).willReturn(Optional.ofNullable(member));

        //when
        MemberDetailDTO MemberDetailDTO = memberService.findById(member.getId());

        //then
        Assertions.assertEquals("홍길동", MemberDetailDTO.getName());
    }

    @Test
    @DisplayName("중복 회원 검증을 진행한다.")
    public void existsByLoginId() {

        //given

        //mocking
        given(memberRepository.existsByLoginId(member.getLoginId())).willReturn(true);

        //when
        boolean result = memberService.existsByLoginId(member.getLoginId());

        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("아이디 찾기에 사용할 회원 정보를 로드한다.")
    public void findByNameAndPhoneNumber() {
        //given
        FindIdDto findIdDto = MemberFactory.makeFindIdDto();

        //mocking
        given(memberRepository.findByNameAndPhoneNumber(findIdDto.getName(), findIdDto.getPhoneNumber())).willReturn(Optional.ofNullable(member));

        //when
        MemberResponseDTO memberResponseDTO1 = memberService.findByNameAndPhoneNumber(findIdDto);

        //then
        Assertions.assertEquals("홍길동", memberResponseDTO1.getName());
    }

    @Test
    @DisplayName("비밀번호 찾기에 사용할 회원 정보를 로드한다.")
    public void findByLoginId() {
        //given

        //mocking
        given(memberRepository.findByLoginId(member.getLoginId())).willReturn(Optional.ofNullable(member));

        //when
        MemberResponseDTO memberResponseDTO = memberService.findByLoginId(member.getLoginId());

        //then
        Assertions.assertEquals("홍길동", memberResponseDTO.getName());
    }

    @Test
    @DisplayName("변경된 회원 정보를 확인한다.")
    public void updateMemberInfo() {
        //mocking
        given(memberRepository.findById(member.getId())).willReturn(Optional.ofNullable(member));

        //when
        MemberDetailDTO memberResponseDTO = memberService.updateMemberInfo(member.getId(), MemberFactory.makeUpdateMemberDetailDTO());

        //then
        Assertions.assertEquals("임꺽정", memberResponseDTO.getName());
    }

    @Test
    @DisplayName("변경된 회원 비밀번호를 확인한다.")
    public void updatePassword() {
        //mocking
        given(memberRepository.findByLoginId(member.getLoginId())).willReturn(Optional.ofNullable(member));

        //when
        String updatePassword = memberService.updatePassword(member.getLoginId(), "gildong1");

        //then
        Assertions.assertEquals("gildong1", updatePassword);
    }
}
