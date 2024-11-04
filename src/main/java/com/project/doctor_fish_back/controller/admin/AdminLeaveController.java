package com.project.doctor_fish_back.controller.admin;

import com.project.doctor_fish_back.dto.admin.request.leave.ReqModifyLeaveDto;
import com.project.doctor_fish_back.dto.admin.request.leave.ReqRegisterLeaveDto;
import com.project.doctor_fish_back.dto.admin.request.reservation.ReqPageAndLimitDto;
import com.project.doctor_fish_back.service.admin.AdminLeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminLeaveController {

    @Autowired
    private AdminLeaveService leaveService;

    // 연차 등록
    @PostMapping("/leave")
    public ResponseEntity<?> registerLeave(@RequestBody ReqRegisterLeaveDto dto) {
        return ResponseEntity.ok().body(leaveService.registerLeave(dto));
    }

    // 관리자 연차 전체 조회
    @GetMapping("/leave/list")
    public ResponseEntity<?> getAllLeavesByUserId(ReqPageAndLimitDto dto) {
        return ResponseEntity.ok().body(leaveService.getLeavesByUserId(dto));
    }

    // 연차 전체 조회
    @GetMapping("/leave/list/info")
    public ResponseEntity<?> getAllLeaves(ReqPageAndLimitDto dto) {
        return ResponseEntity.ok().body(leaveService.getLeaves(dto));
    }

    // 연차 단건 조회
    @GetMapping("/leave/{leaveId}")
    public ResponseEntity<?> getLeaveToDoctorAndInfo(@PathVariable Long leaveId) {
        return ResponseEntity.ok().body(leaveService.getLeaveToDoctorAndInfo(leaveId));
    }

    // 연차 수정
    @PutMapping("/leave/{leaveId}")
    public ResponseEntity<?> modifyLeave(@PathVariable Long leaveId, @RequestBody ReqModifyLeaveDto dto) {
        return ResponseEntity.ok().body(leaveService.modifyLeave(leaveId, dto));
    }

    // 연차 수락
    @PutMapping("/leave/accept/{leaveId}")
    public ResponseEntity<?> acceptLeave(@PathVariable Long leaveId) {
        return ResponseEntity.ok().body(leaveService.acceptLeave(leaveId));
    }

    // 연차 취소
    @PutMapping("/leave/cancel/{leaveId}")
    public ResponseEntity<?> cancelLeave(@PathVariable Long leaveId) {
        return ResponseEntity.ok().body(leaveService.cancelLeave(leaveId));
    }

    // 연차 삭제
    @DeleteMapping("/leave/{leaveId}")
    public ResponseEntity<?> deleteLeave(@PathVariable Long leaveId) {
        return ResponseEntity.ok().body(leaveService.deleteLeave(leaveId));
    }

}
