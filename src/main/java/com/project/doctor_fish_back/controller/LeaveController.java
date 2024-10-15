package com.project.doctor_fish_back.controller;

import com.project.doctor_fish_back.dto.request.leave.ReqModifyLeaveDto;
import com.project.doctor_fish_back.dto.request.leave.ReqRegisterLeaveDto;
import com.project.doctor_fish_back.exception.AuthorityException;
import com.project.doctor_fish_back.service.LeaveService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    // 연차 등록
    @PostMapping("/leave")
    public ResponseEntity<?> registerLeave(@RequestBody ReqRegisterLeaveDto dto) {
        return ResponseEntity.ok().body(leaveService.registerLeave(dto));
    }

    // 의사 연차 전체 조회
    @GetMapping("/leave/list/doctor/{doctorId}")
    public ResponseEntity<?> getAllLeavesToDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok().body(leaveService.getAllLeavesToDoctor(doctorId));
    }

    // 원무과 연차 전체 조회
    @GetMapping("/leave/list/info")
    public ResponseEntity<?> getAllLeavesToInfo() {
        return ResponseEntity.ok().body(leaveService.getAllLeavesToInfo());
    }

    // 의사, 원무과 연차 단건 조회
    @GetMapping("/leave/{leaveId}")
    public ResponseEntity<?> getLeaveToDoctorAndInfo(@PathVariable Long leaveId) throws NotFoundException {
        return ResponseEntity.ok().body(leaveService.getLeaveToDoctorAndInfo(leaveId));
    }

    // 연차 수정
    @PutMapping("/leave/{leaveId}")
    public ResponseEntity<?> modifyLeave(@PathVariable Long leaveId, @RequestBody ReqModifyLeaveDto dto) throws NotFoundException, AuthorityException {
        return ResponseEntity.ok().body(leaveService.modifyLeave(leaveId, dto));
    }

    // 연차 수락
    @PutMapping("/leave/accept/{leaveId}")
    public ResponseEntity<?> acceptLeave(@PathVariable Long leaveId) throws NotFoundException {
        return ResponseEntity.ok().body(leaveService.acceptLeave(leaveId));
    }

    // 연차 취소
    @PutMapping("/leave/cancel/{leaveId}")
    public ResponseEntity<?> cancelLeave(@PathVariable Long leaveId) throws NotFoundException {
        return ResponseEntity.ok().body(leaveService.cancelLeave(leaveId));
    }

    // 연차 삭제
    @DeleteMapping("/leave/{leaveId}")
    public ResponseEntity<?> deleteLeave(@PathVariable Long leaveId) throws NotFoundException {
        return ResponseEntity.ok().body(leaveService.deleteLeave(leaveId));
    }

}
