package com.project.doctor_fish_back.service;

import com.project.doctor_fish_back.entity.Password;
import com.project.doctor_fish_back.entity.User;
import com.project.doctor_fish_back.repository.admin.AdminUserMapper;
import com.project.doctor_fish_back.repository.user.UserPasswordMapper;
import com.project.doctor_fish_back.repository.user.UserUserMapper;
import com.project.doctor_fish_back.security.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String fromEmail;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserUserMapper userMapper;
    @Autowired
    private UserPasswordMapper passwordMapper;

    public Boolean send(String toEmail, String fromEmail, String subject, String content) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");
            // 전송하는사람의 메일
            helper.setFrom(fromEmail);
            // 받는사람의 메일
            helper.setTo(toEmail);
            // 메일의 제목
            helper.setSubject(subject);
            // 메일의 내용
            message.setText(content, "utf-8", "html");

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        javaMailSender.send(message);
        return true;
    }

    public Boolean sendAuthMail(String toEmail) {
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<div style='display:flex;flex-direction:column;justify-content:center;align-items:center;flex-direction:column;width:100%;'>");
        htmlContent.append("<h2>회원가입을 완료하시려면 아래의 인증하기 버튼을 클릭하세요.</h2>");
        htmlContent.append("<a target='_blank' href='http://localhost:8080/auth/mail?token=");
        htmlContent.append(jwtProvider.generateEmailValidToken(toEmail));
        htmlContent.append("'>인증하기</a>");
        htmlContent.append("</div>");

        return send(toEmail, fromEmail, "MEDIBOOK 가입을 위한 인증메일입니다.", htmlContent.toString());
    }

    public Boolean sendResetPasswordMail(String toEmail) {
        User user = userMapper.findByEmail(toEmail);

        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<div style='display:flex;flex-direction:column;justify-content:center;align-items:center;flex-direction:column;width:100%;'>");
        htmlContent.append("<h2>비밀번호 재설정을 하시려면 아래의 재설정 버튼을 클릭하세요.</h2>");
        htmlContent.append("<a target='_blank' href='http://localhost:8080/auth/reset/mail?email=");
        htmlContent.append(user.getEmail());
        htmlContent.append("'>재설정</a>");
        htmlContent.append("</div>");

        return send(toEmail, fromEmail, "MEDIBOOK 비밀번호 재설정을 위한 인증메일입니다.", htmlContent.toString());
    }

    public String validToken(String token) {
        try {
            Claims claims = jwtProvider.getClaims(token);
            String email = claims.get("email").toString();
            User user = userMapper.findByEmail(email);
            if(user == null) {
                return "notFoundUser";
            }
            if(user.getEmailValid() == 1) {
                return "verified";
            }
            userMapper.modifyEmailValidByEmail(user.getEmail());
        } catch (Exception e) {
            return "validTokenFail";
        }
        return "success";
    }

    public Boolean emailValidResult(HttpServletResponse response, String token) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        switch (validToken(token)) {
            case "validTokenFail":
            case "notFoundUser":
                response.getWriter().println(errorView("유효하지 않은 인증 요청입니다."));
                break;
            case "verified":
                response.getWriter().println(errorView("이미 인증 완료된 계정입니다."));
                break;
            case "success":
                response.getWriter().println(successView());
        }
        return true;
    }

    public String emailPasswordValid(String email) {
        try {
            passwordMapper.modifyStatusByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }


    private String successView() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<body>");
        sb.append("<script>");
        sb.append("alert('인증이 완료되었습니다.');");
        sb.append("window.location.replace('http://localhost:3000/user/login')");
        sb.append("</script>");
        sb.append("</body>");
        sb.append("</html>");

        return sb.toString();
    }

    private String errorView(String message) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<body>");
        sb.append("<div style=\"text-align: center;\">");
        sb.append("<h2>");
        sb.append(message);
        sb.append("</h2>");
        sb.append("<button onclick='window.close()'>닫기</button>");
        sb.append("</div>");
        sb.append("</body>");
        sb.append("</html>");

        return sb.toString();
    }

}