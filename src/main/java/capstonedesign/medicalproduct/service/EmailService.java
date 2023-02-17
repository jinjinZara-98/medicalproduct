package capstonedesign.medicalproduct.service;

import capstonedesign.medicalproduct.MailUtils;
import capstonedesign.medicalproduct.dto.member.MemberResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Service
@AllArgsConstructor
public class EmailService {

    private JavaMailSender mailSender;

    public void sendPassword(MemberResponseDTO member) {

        try {
            MailUtils sendMail = new MailUtils(mailSender);
            sendMail.setSubject("JJ Medical " + member.getName()  + " 회원님의 비밀번호 변경 링크입니다.");
            sendMail.setText(new StringBuffer().append("<h1>[비밀번호 변경 링크입니다.]</h1>")
                    .append("<p>아래 링크를 클릭하시면 비밀번호를 변경하세요.</p>")
                    .append("<a href='http://localhost:8080/members/find/password/change/?loginId=")
                    .append(member.getLoginId())
                    .append("'>이메일 인증 확인</a>")
                    .toString());
            sendMail.setFrom("Mr.Heo55@gmail.com","JJ Medical");
            sendMail.setTo(member.getEmail());
            sendMail.send();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}