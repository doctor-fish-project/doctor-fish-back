package com.project.doctor_fish_back.controller;

import com.project.doctor_fish_back.dto.time.ReqReservedTimeDto;
import com.project.doctor_fish_back.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeController {

    @Autowired
    private TimeService timeService;

    @GetMapping("/times")
    public ResponseEntity<?> getTimes(String leaveDate) {
        System.out.println(leaveDate);
        return ResponseEntity.ok().body(timeService.getTimeList(leaveDate));    }

    @GetMapping("/times/reserved")
    public ResponseEntity<?> getReservedTimes(ReqReservedTimeDto dto) {
        return ResponseEntity.ok().body(timeService.getReservedTimeList(dto));
    }
}
