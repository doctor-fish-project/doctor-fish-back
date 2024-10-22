package com.project.doctor_fish_back.controller.admin;

import com.project.doctor_fish_back.service.admin.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminRoleController {

    @Autowired
    AdminRoleService adminRoleService;

    // role 가져오기
    @GetMapping("/roles")
    public ResponseEntity<?> getRoles() {
        return ResponseEntity.ok().body(adminRoleService.getRoles());
    }

}
