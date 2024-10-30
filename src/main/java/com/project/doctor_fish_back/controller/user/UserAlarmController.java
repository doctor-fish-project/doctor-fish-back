package com.project.doctor_fish_back.controller.user;

import com.project.doctor_fish_back.service.user.UserAlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserAlarmController {

    @Autowired
    private UserAlarmService userAlarmService;

    @GetMapping("/alarms")
    public ResponseEntity<?> getAlarms() {
        return ResponseEntity.ok().body(userAlarmService.getAlarms());
    }

    @PostMapping("/alarms")
    public ResponseEntity<?> checkAlarms(@RequestBody List<Long> alarmsId) {
        return ResponseEntity.ok().body(userAlarmService.checkAlarms(alarmsId));
    }
}
