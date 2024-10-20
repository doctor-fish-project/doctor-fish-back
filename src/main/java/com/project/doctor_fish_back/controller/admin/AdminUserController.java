package com.project.doctor_fish_back.controller.admin;

import com.project.doctor_fish_back.aspect.annotation.ValidAop;
import com.project.doctor_fish_back.dto.admin.request.reservation.ReqPageAndLimitDto;
import com.project.doctor_fish_back.dto.admin.request.user.ReqModifyAdminUsernameDto;
import com.project.doctor_fish_back.dto.admin.request.user.ReqModifyUserDto;
import com.project.doctor_fish_back.dto.admin.request.user.ReqModifyUserPasswordDto;
import com.project.doctor_fish_back.service.admin.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired
    private AdminUserService userService;

    // 사용자 전체 조회
    @GetMapping("/user/list")
    public ResponseEntity<?> getUserList(ReqPageAndLimitDto dto) {
        return ResponseEntity.ok().body(userService.getUserList(dto));
    }

    // 원무과, 관리자 정보 수정(이름, 전화번호, 프로필사진)
    @ValidAop
    @PutMapping("/user/{userId}")
    public ResponseEntity<?> modifyUser(@PathVariable Long userId, @Valid @RequestBody ReqModifyUserDto dto, BindingResult bindingResult) {
        return ResponseEntity.ok().body(userService.modifyUser(userId, dto));
    }

    // 원무과, 의사, 관리자 아이디 수정
    @ValidAop
    @PutMapping("/user/{userId}/username")
    public ResponseEntity<?> modifyAdminUsername(@PathVariable Long userId, @Valid @RequestBody ReqModifyAdminUsernameDto dto, BindingResult bindingResult) {
        return ResponseEntity.ok().body(userService.modifyAdminUsername(userId, dto));
    }

    // 사용자, 원무과, 의사, 관리자 비밀번호 변경
    @ValidAop
    @PutMapping("/user/{userId}/password")
    public ResponseEntity<?> modifyUserPassword(@PathVariable Long userId, @Valid @RequestBody ReqModifyUserPasswordDto dto, BindingResult bindingResult) {
        return ResponseEntity.ok().body(userService.modifyUserPassword(userId, dto));
    }

}
