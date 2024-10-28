package com.project.doctor_fish_back.controller.user;

import com.project.doctor_fish_back.dto.user.request.reservation.ReqPageAndLimitDto;
import com.project.doctor_fish_back.service.user.UserAnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserAnnouncementController {

    @Autowired
    private UserAnnouncementService announcementService;

    // 공지사항 전체조회
    @GetMapping("/announce/list")
    public ResponseEntity<?> getAllAnnouncements(ReqPageAndLimitDto dto) {
        return ResponseEntity.ok().body(announcementService.getAllAnnouncements(dto));
    }

    // 공지사항 단건조회
    @GetMapping("/announce/{announceId}")
    public ResponseEntity<?> getAnnouncement(@PathVariable Long announceId) {
        return ResponseEntity.ok().body(announcementService.getAnnouncement(announceId));
    }



}
