package com.project.doctor_fish_back.controller;

import com.project.doctor_fish_back.aspect.annotation.ValidAop;
import com.project.doctor_fish_back.dto.request.doctor.ReqModifyDoctorDto;
import com.project.doctor_fish_back.dto.request.doctor.ReqModifyDoctorPasswordDto;
import com.project.doctor_fish_back.dto.request.user.ReqModifyAdminUsernameDto;
import com.project.doctor_fish_back.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // 의사 전체 조회
    @GetMapping("/doctor/list")
    public ResponseEntity<?> getDoctors() {
        return ResponseEntity.ok().body(doctorService.getDoctors());
    }

    // 의사 정보 수정(이름, 전화번호, 사진, 소개글, 이력)
    @ValidAop
    @PutMapping("/doctor/{doctorId}")
    public ResponseEntity<?> modifyDoctor(@PathVariable Long doctorId, @Valid @RequestBody ReqModifyDoctorDto dto, BindingResult bindingResult) {
        return ResponseEntity.ok().body(doctorService.modifyDoctor(doctorId, dto));
    }

    // 의사 삭제
    @DeleteMapping("/doctor/{doctorId}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok().body(doctorService.deleteDoctor(doctorId));
    }

}