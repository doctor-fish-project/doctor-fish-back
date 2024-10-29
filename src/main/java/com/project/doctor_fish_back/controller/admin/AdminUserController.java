package com.project.doctor_fish_back.controller.admin;

import com.project.doctor_fish_back.aspect.annotation.ValidAop;
import com.project.doctor_fish_back.dto.admin.request.reservation.ReqPageAndLimitDto;
import com.project.doctor_fish_back.dto.admin.request.user.ReqModifyAdminUsernameDto;
import com.project.doctor_fish_back.dto.admin.request.user.ReqModifyUserDto;
import com.project.doctor_fish_back.dto.admin.request.user.ReqModifyUserPasswordDto;
import com.project.doctor_fish_back.dto.search.ReqSearchDto;
import com.project.doctor_fish_back.security.principal.PrincipalUser;
import com.project.doctor_fish_back.service.admin.AdminUserService;
import com.project.doctor_fish_back.service.user.UserUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ResponseEntity<?> getUserList(ReqPageAndLimitDto dto, String searchText) {
        return ResponseEntity.ok().body(userService.getUserList(dto, searchText));
    }

    // 관리자 정보 수정(이름, 전화번호, 사진, 소개글, 이력)
    @ValidAop
    @PutMapping("/user")
    public ResponseEntity<?> modifyUser(@Valid @RequestBody ReqModifyUserDto dto, BindingResult bindingResult) {
        System.out.println(dto);
        return ResponseEntity.ok().body(userService.modifyUser(dto));
    }

    // 관리자 아이디 수정
    @ValidAop
    @PutMapping("/user/username")
    public ResponseEntity<?> modifyAdminUsername(@Valid @RequestBody ReqModifyAdminUsernameDto dto, BindingResult bindingResult) {
        return ResponseEntity.ok().body(userService.modifyAdminUsername(dto));
    }

    // 관리자 비밀번호 변경
    @ValidAop
    @PutMapping("/user/password")
    public ResponseEntity<?> modifyUserPassword(@Valid @RequestBody ReqModifyUserPasswordDto dto, BindingResult bindingResult) {
        return ResponseEntity.ok().body(userService.modifyUserPassword(dto));
    }

    // 회원 탈퇴
    @DeleteMapping("/user")
    public ResponseEntity<?> deleteUser() {
        return ResponseEntity.ok().body(userService.deleteUser());
    }

    // 관리자 본인 정보 조회
    @GetMapping("/user/me")
    public ResponseEntity<?> getMyInfo() {
        return ResponseEntity.ok().body(userService.getMyInfo());
    }

}
