package com.project.doctor_fish_back.controller;

import com.project.doctor_fish_back.security.jwt.JwtProvider;
import com.project.doctor_fish_back.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    // 이메일 인증 요청
    @PostMapping("/auth/mail")
    public ResponseEntity<?> sendAuthEmail(@RequestBody Map<String, Object> dto) {
        return ResponseEntity.ok().body(emailService.sendAuthMail(dto.get("email").toString()));
    }

    // 회원가입 이메일 인증 확인
    @GetMapping("/auth/mail")
    public void emailValid(HttpServletResponse response, @RequestParam String token) throws IOException {
        emailService.emailValidResult(response, token);
    }

    // 비밀번호 변경 이메일 인증 확인
    @GetMapping("/auth/password/mail")
    public void passwordEmailValid(HttpServletResponse response, @RequestParam String token) throws IOException {
        emailService.passwordEmailValidResult(response, token);
    }

}