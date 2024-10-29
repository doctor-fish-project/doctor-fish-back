package com.project.doctor_fish_back.controller.admin;

import com.project.doctor_fish_back.service.admin.AdminDepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminDepartController {

    @Autowired
    private AdminDepartService adminDepartService;

    // 부서 가져오기
    @GetMapping("/depart/list")
    public ResponseEntity<?> getDepartList() {
        return ResponseEntity.ok().body(adminDepartService.getDeparts());
    }


}
