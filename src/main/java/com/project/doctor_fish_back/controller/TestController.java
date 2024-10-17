package com.project.doctor_fish_back.controller;

import com.project.doctor_fish_back.repository.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestMapper testMapper;

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok(testMapper.test());
    }
}
