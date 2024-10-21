package com.project.doctor_fish_back.controller.user;

import com.project.doctor_fish_back.service.user.UserDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDoctorController {

    @Autowired
    private UserDoctorService doctorService;

    // 의사 전체 조회
    @GetMapping("/doctor/list")
    public ResponseEntity<?> getDoctors() {
        return ResponseEntity.ok().body(doctorService.getDoctors());
    }

}