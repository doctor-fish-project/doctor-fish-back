package com.project.doctor_fish_back.controller.admin;

import com.project.doctor_fish_back.dto.admin.request.reservation.ReqPageAndLimitDto;
import com.project.doctor_fish_back.dto.admin.request.reservation.ReqYearDto;
import com.project.doctor_fish_back.dto.search.ReqSearchDto;
import com.project.doctor_fish_back.exception.AuthorityException;
import com.project.doctor_fish_back.repository.admin.AdminReservationMapper;
import com.project.doctor_fish_back.service.admin.AdminReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminReservationController {

    @Autowired
    private AdminReservationService reservationService;

    // 예약 수락
    @PutMapping("/reservation/accept/{reservationId}")
    public ResponseEntity<?> acceptReservation(@PathVariable Long reservationId) {
        return ResponseEntity.ok().body(reservationService.acceptReservation(reservationId));
    }

    // 예약 취소
    @PutMapping("/reservation/cancel/{reservationId}")
    public ResponseEntity<?> cancelReservation(@PathVariable Long reservationId) {
        return ResponseEntity.ok().body(reservationService.cancelReservation(reservationId));
    }

    // 대쉬보드 월 별 의사 별 예약 전체 조회
    @GetMapping("/reservation/dashboard/month/doctor/count")
    public ResponseEntity<?> getAllReservationsMonth(@RequestParam Integer year) {
        return ResponseEntity.ok().body(reservationService.getReservationCountMonthAndDoctor(year));
    }

    // 대쉬보드 전체 예약 조회
    @GetMapping("/reservation/dashboard/all")
    public ResponseEntity<?> getDashBoardReservationAll() {
        return ResponseEntity.ok().body(reservationService.getDashBoardReservations());
    }

    // 대쉬보드 오늘 예약 조회
    @GetMapping("/reservation/dashboard/today")
    public ResponseEntity<?> getDashBoardReservationToday() {
        return ResponseEntity.ok().body(reservationService.getDashBoardReservationsToday());
    }

    // 대쉬보드 주간 예약 수 조회
    @GetMapping("/reservation/dashboard/week/count")
    public ResponseEntity<?> getDashBoardWeekReservationCount() {
        return ResponseEntity.ok().body(reservationService.getDashBoardWeekReservationCount());
    }

    // 대쉬보드 월별 예약 수 조회
    @GetMapping("/reservation/dashboard/month/count")
    public ResponseEntity<?> getDashBoardMonthReservationCount(@RequestParam Integer year) {
        return ResponseEntity.ok().body(reservationService.getReservationCountMonth(year));
    }

    // 관리자 페이지 전체 예약 조회
    @GetMapping("/reservation/all")
    public ResponseEntity<?> getReservationAll(ReqPageAndLimitDto dto, String searchText) {
        return ResponseEntity.ok().body(reservationService.getReservations(dto, searchText));
    }

    // 관리자 페이지 오늘 예약 조회
    @GetMapping("/reservation/today")
    public ResponseEntity<?> getReservationToday(ReqPageAndLimitDto dto, String searchText) {
        return ResponseEntity.ok().body(reservationService.getReservationsToday(dto, searchText));
    }

    // 관리자 예약 삭제
    @DeleteMapping("/reservation/{reservationId}")
    public ResponseEntity<?> deleteReservationFromAdmin(@PathVariable Long reservationId) throws AuthorityException {
        return ResponseEntity.ok().body(reservationService.deleteReservationFromAdmin(reservationId));
    }

    // 관리자 페이지 연도 조회
    @GetMapping("reservation/year")
    public ResponseEntity<?> getYears() {
        return ResponseEntity.ok().body(reservationService.getYears());
    }

}