package com.project.doctor_fish_back.controller.admin;

import com.project.doctor_fish_back.aspect.annotation.ValidAop;
import com.project.doctor_fish_back.dto.admin.request.doctor.ReqModifyDoctorDto;
import com.project.doctor_fish_back.service.admin.AdminDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminDoctorController {

    @Autowired
    private AdminDoctorService doctorService;

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