package capstonedesign.medicalproduct.factory.member;

import capstonedesign.medicalproduct.dto.member.FindIdDto;
import capstonedesign.medicalproduct.dto.member.MemberDetailDto;
import capstonedesign.medicalproduct.dto.member.MemberRequestDto;
import capstonedesign.medicalproduct.factory.BasicFactory;

public class MemberFactory extends BasicFactory {

    public static MemberRequestDto makeMemberRequestDto() {
        MemberRequestDto memberRequestDto = MemberRequestDto.builder()
                                            .loginId("jeong123")
                                            .password("jeong")
                                            .name("임꺽정")
                                            .phoneNumber("01012345678")
                                            .address("충북 충주시 대소원면 대학로 50")
                                            .addressDetail("대원생활관")
                                            .email("jeong123@naver.com")
                                            .accountHost("임꺽정")
                                            .bankName("농협")
                                            .accountNumber("3520459732493").build();

        return memberRequestDto;
    }

    //MemberService 단위 테스트 회원 정보 수정에 사용
    public static MemberDetailDto makeUpdateMemberDetailDTO() {
        MemberDetailDto memberRequestDto = MemberDetailDto.builder()
                .name("임꺽정")
                .phoneNumber("01012341234")
                .address("충북 충주시 대소원면 대학로 50")
                .addressDetail("예성생활관")
                .email("gildong123@naver.com")
                .accountHost("홍길동")
                .bankName("농협")
                .accountNumber("3520459732493").build();

        return memberRequestDto;
    }

    public static FindIdDto makeFindIdDto() {
        return new FindIdDto("홍길동", "01012341234");
    }
}
