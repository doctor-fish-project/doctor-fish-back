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

    // 리뷰 전체 조회
    @GetMapping("/review/list")
    public ResponseEntity<?> getReviews() {
        return ResponseEntity.ok().body(reviewService.getReviews());
    }

    // 관리자 페이지 전체 리뷰 조회
    @GetMapping("/review")
    public ResponseEntity<?> getReviewAllByLimit(ReqPageAndLimitDto dto) {
        return ResponseEntity.ok().body(reviewService.getReviewAllByLimit(dto));
    }

    // 리뷰 좋아요
    @PostMapping("/review/like/{reviewId}")
    public ResponseEntity<?> like(@PathVariable Long reviewId) throws ReviewLikeException {
        return ResponseEntity.ok().body(reviewService.like(reviewId));
    }

    // 리뷰 좋아요 취소
    @DeleteMapping("/review/like/{reviewLikeId}")
    public ResponseEntity<?> dislike(@PathVariable Long reviewLikeId) {
        return ResponseEntity.ok().body(reviewService.dislike(reviewLikeId));
    }

    // 리뷰 좋아요 수 조회
    @GetMapping("/review/like/{reviewId}")
    public ResponseEntity<?> getLikeCount(@PathVariable Long reviewId) {
        return ResponseEntity.ok().body(reviewService.getLikeCount(reviewId));
    }

    // 리뷰 검색
    @GetMapping("/review/search")
    public ResponseEntity<?> searchReview(ReqSearchDto dto) {
        return ResponseEntity.ok().body(reviewService.searchReview(dto));
    }

}
