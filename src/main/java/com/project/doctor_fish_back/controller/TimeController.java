package com.project.doctor_fish_back.controller;

import com.project.doctor_fish_back.dto.request.time.ReqReservedTimeDto;
import com.project.doctor_fish_back.service.TimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TimeController {

    @Autowired
    private TimeService timeService;

    @GetMapping("/times")
    public ResponseEntity<?> getTimes() {
        return ResponseEntity.ok().body(timeService.getTimeList());    }

    @GetMapping("/times/reserved")
    public ResponseEntity<?> getReservedTimes(ReqReservedTimeDto dto) {
        System.out.println("요청");
        log.info("{}", dto.getReservationDate());
        return ResponseEntity.ok().body(timeService.getReservedTimeList(dto));    }
}
