package com.project.doctor_fish_back.controller.admin;

import com.project.doctor_fish_back.aspect.annotation.ValidAop;
import com.project.doctor_fish_back.dto.admin.request.auth.ReqAccessDto;
import com.project.doctor_fish_back.dto.admin.request.auth.ReqAdminSigninDto;
import com.project.doctor_fish_back.dto.admin.request.auth.ReqAdminSignupDto;
import com.project.doctor_fish_back.exception.SigninException;
import com.project.doctor_fish_back.exception.SignupException;
import com.project.doctor_fish_back.service.TokenService;
import com.project.doctor_fish_back.service.admin.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminAuthenticationController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AdminUserService userService;

    // 토큰 유효성 검사
    @GetMapping("/auth/access")
    public ResponseEntity<?> access(ReqAccessDto dto) {
        return ResponseEntity.ok().body(tokenService.isValidAccessToken(dto.getAdminAccessToken()));
    }

    // 원무과, 의사, 관리자 회원가입
    @ValidAop
    @PostMapping("/auth/signup")
    public ResponseEntity<?> adminSignup(@Valid @RequestBody ReqAdminSignupDto dto, BindingResult bindingResult) throws SignupException {
        System.out.println(dto);
        return ResponseEntity.ok().body(userService.adminSignup(dto));
    }

    // 원무과, 의사, 관리자 로그인
    @ValidAop
    @PostMapping("/auth/signin")
    public ResponseEntity<?> adminSignin(@Valid @RequestBody ReqAdminSigninDto dto, BindingResult bindingResult) throws SigninException {
        return ResponseEntity.ok().body(userService.getGeneratedAccessToken(dto));
    }

}