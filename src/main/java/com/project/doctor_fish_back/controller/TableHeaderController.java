package com.project.doctor_fish_back.controller;

import com.project.doctor_fish_back.dto.request.tableheader.ReqTableHeaderDto;
import com.project.doctor_fish_back.service.TableHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TableHeaderController {

    @Autowired
    private TableHeaderService tableHeaderService;

    @GetMapping("/tableheader")
    public ResponseEntity<?> getTableHeaders(ReqTableHeaderDto dto) {
        System.out.println(dto);
        return ResponseEntity.ok().body(tableHeaderService.getTableHeaders(dto.getCategoryId()));
    }
}
