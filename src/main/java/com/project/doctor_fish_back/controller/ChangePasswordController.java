package com.project.doctor_fish_back.controller;

import com.project.doctor_fish_back.service.ChangePasswordService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChangePasswordController {

    @Autowired
    private ChangePasswordService changePasswordService;

    // 비밀번호 status 조회
    @GetMapping("/auth/change/password/{userId}")
    public ResponseEntity<?> getChangePassword(@PathVariable Long userId) throws NotFoundException {
        System.out.println(userId);
        return ResponseEntity.ok().body(changePasswordService.getChangePassword(userId));
    }

}
