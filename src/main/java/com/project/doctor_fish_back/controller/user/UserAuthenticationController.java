package com.project.doctor_fish_back.controller.user;

import com.project.doctor_fish_back.aspect.annotation.ValidAop;
import com.project.doctor_fish_back.dto.user.request.auth.ReqAccessDto;
import com.project.doctor_fish_back.dto.user.request.auth.ReqSigninDto;
import com.project.doctor_fish_back.dto.user.request.auth.ReqSignupDto;
import com.project.doctor_fish_back.exception.SigninException;
import com.project.doctor_fish_back.exception.SignupException;
import com.project.doctor_fish_back.service.TokenService;
import com.project.doctor_fish_back.service.user.UserUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserAuthenticationController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserUserService userService;

    // 토큰 유효성 검사
    @GetMapping("/auth/access")
    public ResponseEntity<?> access(ReqAccessDto dto) {
        return ResponseEntity.ok().body(tokenService.isValidAccessToken(dto.getAccessToken()));
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