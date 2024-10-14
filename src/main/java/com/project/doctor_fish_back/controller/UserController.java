package com.project.doctor_fish_back.controller;

import com.project.doctor_fish_back.aspect.annotation.ValidAop;
import com.project.doctor_fish_back.dto.request.user.ReqModifyUserDto;
import com.project.doctor_fish_back.dto.request.user.ReqModifyUserEmailDto;
import com.project.doctor_fish_back.dto.request.user.ReqModifyUserPasswordDto;
import com.project.doctor_fish_back.exception.AuthorityException;
import com.project.doctor_fish_back.security.principal.PrincipalUser;
import com.project.doctor_fish_back.service.UserService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // 사용자 전체 조회
    @GetMapping("/user/list")
    public ResponseEntity<?> getUserList() {
        return ResponseEntity.ok().body(userService.getUserList());
    }

    // 사용자 내 정보 조회
    @GetMapping("/user/me")
    public ResponseEntity<?> getUserMe() {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(userService.getUserInfo(principalUser.getId()));
    }

    // 사용자 정보 수정(이름, 전화번호, 프로필사진)
    @ValidAop
    @PutMapping("/user/{userId}")
    public ResponseEntity<?> modifyUser(@PathVariable Long userId, @Valid @RequestBody ReqModifyUserDto dto, BindingResult bindingResult) throws NotFoundException, AuthorityException {
        return ResponseEntity.ok().body(userService.modifyUser(userId, dto));
    }

    // 사용자 이메일 변경
    @ValidAop
    @PutMapping("/user/{userId}/email")
    public ResponseEntity<?> modifyUserEmail(@PathVariable Long userId, @Valid @RequestBody ReqModifyUserEmailDto dto, BindingResult bindingResult) throws NotFoundException, AuthorityException {
        return ResponseEntity.ok().body(userService.modifyUserEmail(userId, dto));
    }

    // 사용자 비밀번호 변경
    @ValidAop
    @PutMapping("/user/{userId}/password")
    public ResponseEntity<?> modifyUserPassword(@PathVariable Long userId, @Valid @RequestBody ReqModifyUserPasswordDto dto, BindingResult bindingResult) throws NotFoundException, AuthorityException {
        return ResponseEntity.ok().body(userService.modifyUserPassword(userId, dto));
    }

    // 회원 탈퇴
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) throws NotFoundException, AuthorityException {
        return ResponseEntity.ok().body(userService.deleteUser(userId));
    }
}
