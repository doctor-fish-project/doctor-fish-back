package com.project.doctor_fish_back.controller;

import com.project.doctor_fish_back.dto.request.reservation.ReqRegisterReservationDto;
import com.project.doctor_fish_back.exception.AuthorityException;
import com.project.doctor_fish_back.service.ReservationService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // 예약하기
    @PostMapping("/reservation")
    public ResponseEntity<?> registerReservation(@RequestBody ReqRegisterReservationDto dto) {
        return ResponseEntity.ok().body(reservationService.registerReservation(dto));
    }

    // 예약 수락
    @PutMapping("/reservation/accept/{reservationId}")
    public ResponseEntity<?> acceptReservation(@PathVariable Long reservationId) throws NotFoundException {
        return ResponseEntity.ok().body(reservationService.acceptReservation(reservationId));
    }

    // 예약 취소
    @PutMapping("/reservation/cancel/{reservationId}")
    public ResponseEntity<?> cancelReservation(@PathVariable Long reservationId) throws NotFoundException {
        return ResponseEntity.ok().body(reservationService.cancelReservation(reservationId));
    }

    // 사용자 오늘 예약 조회
    @GetMapping("/reservation/user")
    public ResponseEntity<?> getReservationToUser() {
        return ResponseEntity.ok().body(reservationService.getReservationsToUser());
    }

    // 사용자 예약 전체 조회
    @GetMapping("/reservation/list/user")
    public ResponseEntity<?> getAllReservationsToUser() {
        return ResponseEntity.ok().body(reservationService.getAllReservationsToUser());
    }


    // 사용자 예약 단건 조회
    @GetMapping("/reservation/user/{reservationId}")
    public ResponseEntity<?> getReservationToUser(@PathVariable Long reservationId) throws AuthorityException, NotFoundException {
        return ResponseEntity.ok().body(reservationService.getReservationToUser(reservationId));
    }

    // 관리자 예약 전체 조회
    @GetMapping("/reservation/list/admin")
    public ResponseEntity<?> getAllReservationsToAdmin() {
        return ResponseEntity.ok().body(reservationService.getAllReservationsToAdmin());
    }

    // 관리자 예약 단건 조회
    @GetMapping("/reservation/admin/{reservationId}")
    public ResponseEntity<?> getReservationToAdmin(@PathVariable Long reservationId) throws NotFoundException {
        return ResponseEntity.ok().body(reservationService.getReservationToAdmin(reservationId));
    }

    // 사용자 예약 삭제
    @DeleteMapping("/reservation/user/{reservationId}")
    public ResponseEntity<?> deleteReservationFromUser(@PathVariable Long reservationId) throws NotFoundException, AuthorityException {
        return ResponseEntity.ok().body(reservationService.deleteReservationFromUser(reservationId));
    }

    // 관리자 예약 삭제
    @DeleteMapping("/reservation/admin/{reservationId}")
    public ResponseEntity<?> deleteReservationFromAdmin(@PathVariable Long reservationId) throws NotFoundException, AuthorityException {
        return ResponseEntity.ok().body(reservationService.deleteReservationFromAdmin(reservationId));
    }

}