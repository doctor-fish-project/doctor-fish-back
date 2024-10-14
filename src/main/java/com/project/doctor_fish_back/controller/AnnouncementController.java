package com.project.doctor_fish_back.controller;

import com.project.doctor_fish_back.aspect.annotation.ValidAop;
import com.project.doctor_fish_back.dto.request.announcement.ReqModifyAnnounce;
import com.project.doctor_fish_back.dto.request.announcement.ReqWriteAnnounceDto;
import com.project.doctor_fish_back.exception.AuthorityException;
import com.project.doctor_fish_back.service.AnnouncementService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    // 공지사항 작성
    @ValidAop
    @PostMapping("/announce")
    public ResponseEntity<?> writeAnnounce(@Valid @RequestBody ReqWriteAnnounceDto dto, BindingResult bindingResult) throws NotFoundException, AuthorityException {
        return ResponseEntity.ok().body(announcementService.writeAnnounce(dto));
    }

    // 공지사항 수정
    @ValidAop
    @PutMapping("/announce/{announceId}")
    public ResponseEntity<?> modifyAnnounce(@PathVariable Long announceId, @Valid @RequestBody ReqModifyAnnounce dto, BindingResult bindingResult) throws NotFoundException, AuthorityException {
        return ResponseEntity.ok().body(announcementService.modifyAnnounce(announceId, dto));
    }

    // 공지사항 삭제
    @DeleteMapping("/announce/{announceId}")
    public ResponseEntity<?> deleteAnnounce(@PathVariable Long announceId) throws NotFoundException, AuthorityException {
        return ResponseEntity.ok().body(announcementService.deleteAnnounce(announceId));
    }

    // 공지사항 전체조회
    @GetMapping("/announce/list")
    public ResponseEntity<?> getAllAnnouncements() {
        return ResponseEntity.ok().body(announcementService.getAllAnnouncements());
    }

    // 공지사항 단건조회
    @GetMapping("/announce/{announceId}")
    public ResponseEntity<?> getAnnouncement(@PathVariable Long announceId) throws NotFoundException {
        return ResponseEntity.ok().body(announcementService.getAnnouncement(announceId));
    }



}
