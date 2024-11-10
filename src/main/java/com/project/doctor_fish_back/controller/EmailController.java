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

    // 이메일 패스워드 재설정 인증 요청
    @PostMapping("/auth/reset/mail")
    public ResponseEntity<?> sendResetEmail(@RequestBody Map<String, Object> dto) {
        return ResponseEntity.ok().body(emailService.sendResetPasswordMail(dto.get("email").toString()));
    }

    // 이메일 인증 확인
    @GetMapping("/auth/mail")
    public void emailValid(HttpServletResponse response, @RequestParam String token) throws IOException {
        emailService.emailValidResult(response, token);
    }

    // 이메일 패스워드 재설정 인증 확인
    @GetMapping("/auth/reset/mail")
    public void emailPasswordValid(HttpServletResponse response, @RequestParam String email) throws IOException {
        emailService.emailPasswordValid(email);
    }

}