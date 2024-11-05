package com.project.doctor_fish_back.controller.user;

import com.project.doctor_fish_back.dto.admin.request.reservation.ReqPageAndLimitDto;
import com.project.doctor_fish_back.dto.user.request.reservation.ReqModifyReservationDto;
import com.project.doctor_fish_back.dto.user.request.reservation.ReqRegisterReservationDto;
import com.project.doctor_fish_back.service.user.UserReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserReservationController {

    @Autowired
    private UserReservationService reservationService;

    // 예약하기
    @PostMapping("/reservation")
    public ResponseEntity<?> registerReservation(@RequestBody ReqRegisterReservationDto dto) {
        System.out.println(dto);
        return ResponseEntity.ok().body(reservationService.registerReservation(dto));
    }

    // 예약 취소
    @PutMapping("/reservation/cancel/{reservationId}")
    public ResponseEntity<?> cancelReservation(@PathVariable Long reservationId) {
        return ResponseEntity.ok().body(reservationService.cancelReservation(reservationId));
    }

    // 사용자 오늘 예약 조회
    @GetMapping("/reservation/today")
    public ResponseEntity<?> getReservationToUser() {
        return ResponseEntity.ok().body(reservationService.getReservationsToday());
    }

    // 사용자 내 예약 전체 조회
    @GetMapping("/reservation/list")
    public ResponseEntity<?> getAllReservationsToUser(ReqPageAndLimitDto dto) {
        return ResponseEntity.ok().body(reservationService.getReservations(dto));
    }

    // 사용자 내 예약 단건 조회
    @GetMapping("/reservation/{reservationId}")
    public ResponseEntity<?> getReservationToUser(@PathVariable Long reservationId) {
        return ResponseEntity.ok().body(reservationService.getReservationById(reservationId));
    }

    // 예약 정보 수정
    @PutMapping("/reservation/{reservationId}")
    public ResponseEntity<?> modifyReservation(@PathVariable Long reservationId, @RequestBody ReqModifyReservationDto dto) {
        System.out.println(dto);
        return ResponseEntity.ok().body(reservationService.modifyReservation(reservationId, dto));
    }

    // 사용자 예약 삭제
    @DeleteMapping("/reservation/{reservationId}")
    public ResponseEntity<?> deleteReservationFromUser(@PathVariable Long reservationId) {
        return ResponseEntity.ok().body(reservationService.deleteReservation(reservationId));
    }

    // 리뷰 작성 가능한 예약 전체 조회
    @GetMapping("/reservation/end")
    public ResponseEntity<?> getEndReservation() {
        return ResponseEntity.ok().body(reservationService.getEndReservation());
    }

}