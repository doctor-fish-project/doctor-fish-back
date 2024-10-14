package com.project.doctor_fish_back.controller;

import com.project.doctor_fish_back.dto.request.doctor.ReqRegisterDoctorDto;
import com.project.doctor_fish_back.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // 의사 등록
    @PostMapping("/doctor/register")
    public ResponseEntity<?> register(@RequestBody ReqRegisterDoctorDto dto) {
        return ResponseEntity.ok().body(doctorService.insertDoctorAndDepart(dto));
    }

    // 의사 전체 조회
    @GetMapping("/doctor/list")
    public ResponseEntity<?> getDoctors() {
        return ResponseEntity.ok().body(doctorService.getDoctors());
    }
}