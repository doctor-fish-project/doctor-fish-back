package com.project.doctor_fish_back.controller.admin;

import com.project.doctor_fish_back.service.TableHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class TableHeaderController {

    @Autowired
    private TableHeaderService tableHeaderService;

    @GetMapping("/tableheader")
    public ResponseEntity<?> getTableHeaders(@RequestParam String pathName) {
        return ResponseEntity.ok().body(tableHeaderService.getTableHeaders(pathName));
    }
}
