package com.project.doctor_fish_back.controller;

import com.project.doctor_fish_back.dto.request.reservation.ReqRegisterReservationDto;
import com.project.doctor_fish_back.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // 예약하기
    @PostMapping("/reservation")
    public ResponseEntity<?> registerReservation(@RequestBody ReqRegisterReservationDto dto) {
        return ResponseEntity.ok().body(reservationService.registerReservation(dto));
    }

}