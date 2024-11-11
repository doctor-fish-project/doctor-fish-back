package com.project.doctor_fish_back.controller.user;

import com.project.doctor_fish_back.aspect.annotation.ValidAop;
import com.project.doctor_fish_back.dto.user.request.user.ReqModifyUserDto;
import com.project.doctor_fish_back.dto.user.request.user.ReqModifyUserEmailDto;
import com.project.doctor_fish_back.dto.user.request.user.ReqModifyUserPasswordDto;
import com.project.doctor_fish_back.exception.SignupException;
import com.project.doctor_fish_back.security.principal.PrincipalUser;
import com.project.doctor_fish_back.service.user.UserUserService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserUserController {

    @Autowired
    private UserUserService userService;

    // 사용자 내 정보 조회
    @GetMapping("/user/me")
    public ResponseEntity<?> getUserMe() {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(userService.getUserInfo(principalUser.getId()));
    }
    // FIXME: 이 부분 수정 부탁드립니다
    // 사용자 정보 수정(이름, 전화번호, 프로필사진)
    @ValidAop
    @PutMapping("/user")
    public ResponseEntity<?> modifyUser(@Valid @RequestBody ReqModifyUserDto dto, BindingResult bindingResult) {
        return ResponseEntity.ok().body(userService.modifyUser(dto));
    }

    // 사용자 이메일 변경
    @ValidAop
    @PutMapping("/user/email")
    public ResponseEntity<?> modifyUserEmail(@Valid @RequestBody ReqModifyUserEmailDto dto, BindingResult bindingResult) throws SignupException {
        return ResponseEntity.ok().body(userService.modifyUserEmail(dto));
    }

    // 사용자 비밀번호 변경
    @ValidAop
    @PutMapping("/auth/password")
    public ResponseEntity<?> modifyUserPassword(@Valid @RequestBody ReqModifyUserPasswordDto dto, BindingResult bindingResult) {
        System.out.println(dto);
        return ResponseEntity.ok().body(userService.modifyUserPassword(dto));
    }

    // 회원 탈퇴
    @DeleteMapping("/user")
    public ResponseEntity<?> deleteUser() {
        return ResponseEntity.ok().body(userService.deleteUser());
    }

    // 비밀번호 변경할 때 이메일로 사용자 찾기
    @PostMapping("/auth/check")
    public ResponseEntity<?> getUserByEmail(@RequestBody ReqModifyUserEmailDto dto) throws NotFoundException {
        return ResponseEntity.ok().body(userService.getUserByEmail(dto.getEmail()));
    }
}
