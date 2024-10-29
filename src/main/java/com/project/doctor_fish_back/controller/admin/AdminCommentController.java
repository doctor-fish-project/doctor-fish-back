package com.project.doctor_fish_back.controller.admin;

import com.project.doctor_fish_back.aspect.annotation.ValidAop;
import com.project.doctor_fish_back.dto.admin.request.comment.ReqModifyCommentDto;
import com.project.doctor_fish_back.dto.admin.request.comment.ReqRegisterCommentDto;
import com.project.doctor_fish_back.dto.admin.request.reservation.ReqPageAndLimitDto;
import com.project.doctor_fish_back.service.admin.AdminCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminCommentController {

    @Autowired
    private AdminCommentService commentService;

    // 댓글 작성
    @ValidAop
    @PostMapping("/review/comment")
    public ResponseEntity<?> writeComment(@Valid @RequestBody ReqRegisterCommentDto dto, BindingResult bindingResult) {
        return ResponseEntity.ok().body(commentService.writeComment(dto));
    }

    // 댓글 조회
    @GetMapping("/review/{reviewId}/comments")
    public ResponseEntity<?> getComments(@PathVariable Long reviewId, ReqPageAndLimitDto dto) {
        return ResponseEntity.ok().body(commentService.getComments(reviewId, dto));
    }

    // 댓글 수정
    @ValidAop
    @PutMapping("/review/comment")
    public ResponseEntity<?> modifyComment(@Valid @RequestBody ReqModifyCommentDto dto, BindingResult bindingResult) {
        System.out.println(dto);
        return ResponseEntity.ok().body(commentService.modifyComment(dto));
    }

    // 댓글 삭제
    @DeleteMapping("/review/comment/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId) {
        return ResponseEntity.ok().body(commentService.deleteComment(commentId));
    }

}
