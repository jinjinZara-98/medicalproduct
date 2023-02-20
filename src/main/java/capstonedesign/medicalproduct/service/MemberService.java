package capstonedesign.medicalproduct.service;

import capstonedesign.medicalproduct.dto.member.FindIdDto;
import capstonedesign.medicalproduct.domain.entity.Member;
import capstonedesign.medicalproduct.dto.member.MemberDetailDto;
import capstonedesign.medicalproduct.dto.member.MemberRequestDto;
import capstonedesign.medicalproduct.dto.member.MemberResponseDTO;
import capstonedesign.medicalproduct.exception.MemberNotFoundException;
import capstonedesign.medicalproduct.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public long save(MemberRequestDto form) {

        return  memberRepository.save(form.toEntity()).getId();
    }

    public List<MemberDetailDto> findAll() {

        List<Member> members = memberRepository.findAll();

        List<MemberDetailDto> memberDetailDTOS = members.stream()
                .map(member -> new MemberDetailDto(member))
                .collect(Collectors.toList());

        return memberDetailDTOS;
    }

    public MemberDetailDto findById(long memberId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("해당 회원은 없습니다. id = "  + memberId));

        return new MemberDetailDto(member);
    }

    public MemberResponseDTO findByNameAndPhoneNumber(FindIdDto findIdDto) {

        String name = findIdDto.getName();
        String phoneNumber = findIdDto.getPhoneNumber();

        Member member = memberRepository.findByNameAndPhoneNumber(name, phoneNumber)
                .orElseThrow(() -> new MemberNotFoundException("해당 회원은 없습니다. 아이디 = 이름 = " +  name + " 전화번호 = " + phoneNumber));

        return new MemberResponseDTO(member);
    }

    public MemberResponseDTO findByLoginId(String loginId) {

        Member member = memberRepository.findByLoginId(loginId)
                .orElseThrow(() -> new MemberNotFoundException("해당 회원은 없습니다. 아이디 = " + loginId));

        return new MemberResponseDTO(member);
    }

    public boolean existsByLoginId(String joinId) {

        boolean exist = memberRepository.existsByLoginId(joinId);

        if (exist) {
            return true;
        }
        else
            return false;
    }

    @Transactional
    public MemberDetailDto updateMemberInfo(long memberId, MemberDetailDto memberDetailDTO) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("해당 회원은 없습니다. id = " + memberId+ " 이름 = " + memberId));
        Member updatedMember = member.update(memberDetailDTO);

        return new MemberDetailDto(updatedMember);
    }

    @Transactional
    public String updatePassword(String loginId, String password) {
        Member member = memberRepository.findByLoginId(loginId)
                .orElseThrow(() -> new MemberNotFoundException("해당 회원은 없습니다. loginId = " + loginId + " 비밀번호 = " + password));

        return member.updatePassword(password);
    }
}
