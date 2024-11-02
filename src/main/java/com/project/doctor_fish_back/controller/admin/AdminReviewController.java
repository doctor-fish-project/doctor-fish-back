package com.project.doctor_fish_back.controller.admin;

import com.project.doctor_fish_back.dto.admin.request.reservation.ReqPageAndLimitDto;
import com.project.doctor_fish_back.dto.search.ReqSearchDto;
import com.project.doctor_fish_back.exception.ReviewLikeException;
import com.project.doctor_fish_back.service.admin.AdminReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminReviewController {

    @Autowired
    private AdminReviewService reviewService;

    // 리뷰 단건 조회
    @GetMapping("/review/{reviewId}")
    public ResponseEntity<?> getReview(@PathVariable Long reviewId) {
        return ResponseEntity.ok().body(reviewService.getReview(reviewId));
    }

    // 관리자 페이지 전체 리뷰 조회
    @GetMapping("/review")
    public ResponseEntity<?> getReviewAllByLimit(ReqPageAndLimitDto dto, String searchText) {
        return ResponseEntity.ok().body(reviewService.getReviewAllByLimit(dto, searchText));
    }

    // 리뷰 삭제
    @DeleteMapping("/review/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable Long reviewId) {
        return ResponseEntity.ok().body(reviewService.deleteReview(reviewId));
    }

    // 리뷰 좋아요
    @PostMapping("/review/like/{reviewId}")
    public ResponseEntity<?> like(@PathVariable Long reviewId) throws ReviewLikeException {
        return ResponseEntity.ok().body(reviewService.like(reviewId));
    }

    // 리뷰 좋아요 취소
    @DeleteMapping("/review/like/{reviewId}")
    public ResponseEntity<?> dislike(@PathVariable Long reviewId) {
        return ResponseEntity.ok().body(reviewService.dislike(reviewId));
    }

    // 리뷰 좋아요 수 조회
    @GetMapping("/review/like/{reviewId}")
    public ResponseEntity<?> getLikeCount(@PathVariable Long reviewId) {
        return ResponseEntity.ok().body(reviewService.getLikeCount(reviewId));
    }

}
