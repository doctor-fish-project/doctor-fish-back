package com.project.doctor_fish_back.controller;

import com.project.doctor_fish_back.aspect.annotation.ValidAop;
import com.project.doctor_fish_back.dto.request.auth.*;
import com.project.doctor_fish_back.exception.SigninException;
import com.project.doctor_fish_back.exception.SignupException;
import com.project.doctor_fish_back.service.TokenService;
import com.project.doctor_fish_back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AuthenticationController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;

    // 토큰 유효성 검사
    @GetMapping("/auth/access")
    public ResponseEntity<?> access(ReqAccessDto dto) {
        return ResponseEntity.ok().body(tokenService.isValidAccessToken(dto.getAccessToken()));
    }

    // 원무과, 의사, 관리자 회원가입
    @ValidAop
    @PostMapping("/auth/signup/admin")
    public ResponseEntity<?> adminSignup(@Valid @RequestBody ReqAdminSignupDto dto, BindingResult bindingResult) throws SignupException {
        return ResponseEntity.ok().body(userService.adminSignup(dto));
    }

    // 원무과, 의사, 관리자 로그인
    @ValidAop
    @PostMapping("/auth/signin/admin")
    public ResponseEntity<?> adminSignin(@Valid @RequestBody ReqAdminSigninDto dto, BindingResult bindingResult) throws SigninException {
        return ResponseEntity.ok().body(userService.getGeneratedAccessToken(dto));
    }

    // 사용자 회원가입
    @ValidAop
    @PostMapping("/auth/signup")
    public ResponseEntity<?> userSignup(@Valid @RequestBody ReqSignupDto dto, BindingResult bindingResult) throws SignupException {
        return ResponseEntity.ok().body(userService.insertUserAndUserRoles(dto));
    }

    // 사용자 로그인
    @ValidAop
    @PostMapping("/auth/signin")
    public ResponseEntity<?> signin(@Valid @RequestBody ReqSigninDto dto, BindingResult bindingResult) throws SigninException {
        return ResponseEntity.ok().body(userService.getGeneratedAccessToken(dto));
    }
}