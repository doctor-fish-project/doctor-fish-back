package com.project.doctor_fish_back.controller.admin;

import com.project.doctor_fish_back.aspect.annotation.ValidAop;
import com.project.doctor_fish_back.dto.admin.request.announcement.ReqModifyAnnounce;
import com.project.doctor_fish_back.dto.admin.request.announcement.ReqWriteAnnounceDto;
import com.project.doctor_fish_back.dto.admin.request.reservation.ReqPageAndLimitDto;
import com.project.doctor_fish_back.exception.AuthorityException;
import com.project.doctor_fish_back.service.admin.AdminAnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminAnnouncementController {

    @Autowired
    private AdminAnnouncementService announcementService;

    // 공지사항 작성
    @ValidAop
    @PostMapping("/announce")
    public ResponseEntity<?> writeAnnounce(@Valid @RequestBody ReqWriteAnnounceDto dto, BindingResult bindingResult) throws AuthorityException {
        return ResponseEntity.ok().body(announcementService.writeAnnounce(dto));
    }

    // 공지사항 수정
    @ValidAop
    @PutMapping("/announce/{announceId}")
    public ResponseEntity<?> modifyAnnounce(@PathVariable Long announceId, @Valid @RequestBody ReqModifyAnnounce dto, BindingResult bindingResult) {
        return ResponseEntity.ok().body(announcementService.modifyAnnounce(announceId, dto));
    }

    // 공지사항 삭제
    @DeleteMapping("/announce/{announceId}")
    public ResponseEntity<?> deleteAnnounce(@PathVariable Long announceId) {
        return ResponseEntity.ok().body(announcementService.deleteAnnounce(announceId));
    }

    // 공지사항 전체조회
    @GetMapping("/announce/list")
    public ResponseEntity<?> getAllAnnouncements(ReqPageAndLimitDto dto, String searchText) {
        return ResponseEntity.ok().body(announcementService.getAllAnnouncements(dto, searchText));
    }

    // 대쉬보드 공지사항 조회
    @GetMapping("/announce/dashboard/list")
    public ResponseEntity<?> getDashBoardAnnouncements() {
        return ResponseEntity.ok().body(announcementService.getDashBoardAnnouncements());
    }

    // 공지사항 단건조회
    @GetMapping("/announce/{announceId}")
    public ResponseEntity<?> getAnnouncement(@PathVariable Long announceId) {
        return ResponseEntity.ok().body(announcementService.getAnnouncement(announceId));
    }

}
