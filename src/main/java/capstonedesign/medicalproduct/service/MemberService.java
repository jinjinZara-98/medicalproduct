package capstonedesign.medicalproduct.service;

import capstonedesign.medicalproduct.dto.member.FindIdDto;
import capstonedesign.medicalproduct.domain.entity.Member;
import capstonedesign.medicalproduct.dto.member.MemberDetailDTO;
import capstonedesign.medicalproduct.dto.member.MemberRequestDto;
import capstonedesign.medicalproduct.dto.member.MemberResponseDTO;
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
    public MemberDetailDTO save(MemberRequestDto form) {
        Member member = memberRepository.save(form.toEntity());

        return new MemberDetailDTO(member);
    }

    public List<MemberDetailDTO> findAll() {

        List<Member> members = memberRepository.findAll();

        List<MemberDetailDTO> memberDetailDTOS = members.stream()
                .map(member -> new MemberDetailDTO(member))
                .collect(Collectors.toList());

        return memberDetailDTOS;
    }

    public MemberDetailDTO findById(long memberId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원은 없습니다. id = "  + memberId));

        return new MemberDetailDTO(member);
    }

    public MemberResponseDTO findByNameAndPhoneNumber(FindIdDto findIdDto) {

        String name = findIdDto.getName();
        String phoneNumber = findIdDto.getPhoneNumber();

        Member member = memberRepository.findByNameAndPhoneNumber(name, phoneNumber)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원은 없습니다. 아이디 = 이름 = " +  name + " 전화번호 = " + phoneNumber));

        return new MemberResponseDTO(member);
    }

    public MemberResponseDTO findByLoginId(String loginId) {

        Member member = memberRepository.findByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원은 없습니다. 아이디 = " + loginId));

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
    public MemberDetailDTO updateMemberInfo(long memberId, MemberDetailDTO memberDetailDTO) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원은 없습니다. id = " + memberId+ " 이름 = " + memberId));
        Member updatedMember = member.update(memberDetailDTO);

        return new MemberDetailDTO(updatedMember);
    }

    @Transactional
    public String updatePassword(String loginId, String password) {
        Member member = memberRepository.findByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원은 없습니다. loginId = " + loginId + " 비밀번호 = " + password));

        return member.updatePassword(password);
    }
}
