package com.project.doctor_fish_back.controller;

import com.project.doctor_fish_back.aspect.annotation.ValidAop;
import com.project.doctor_fish_back.dto.request.reservation.ReqPageAndLimitDto;
import com.project.doctor_fish_back.dto.request.review.ReqModifyReviewDto;
import com.project.doctor_fish_back.dto.request.review.ReqWriteReviewDto;
import com.project.doctor_fish_back.exception.AuthorityException;
import com.project.doctor_fish_back.exception.ReviewLikeException;
import com.project.doctor_fish_back.service.ReviewService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // 리뷰 작성
    @ValidAop
    @PostMapping("/review")
    public ResponseEntity<?> writeReview(@Valid @RequestBody ReqWriteReviewDto dto, BindingResult bindingResult) {
        System.out.println(dto);
        return ResponseEntity.ok().body(reviewService.writeReview(dto));
    }

    @GetMapping("/review")
    public ResponseEntity<?> getReviewAll() {
        return ResponseEntity.ok().body(reviewService.getReviewAll());
    }

    // 관리자 페이지 전체 리뷰 조회
    @GetMapping("/admin/review")
    public ResponseEntity<?> getReviewAllByLimit(ReqPageAndLimitDto dto) {
        return ResponseEntity.ok().body(reviewService.getReviewAllByLimit(dto));
    }

    // 사용자 내 리뷰 전체 조회
    @GetMapping("/review/me")
    public ResponseEntity<?> getReviewsToUser() {
        return ResponseEntity.ok().body(reviewService.getReviewsToUser());
    }

    // 리뷰 수정
    @ValidAop
    @PutMapping("/review/{reviewId}")
    public ResponseEntity<?> modifyReview(@PathVariable Long reviewId, @Valid @RequestBody ReqModifyReviewDto dto, BindingResult bindingResult) {
        return ResponseEntity.ok().body(reviewService.modifyReview(reviewId, dto));
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
    @DeleteMapping("/review/like/{reviewLikeId}")
    public ResponseEntity<?> dislike(@PathVariable Long reviewLikeId) {
        return ResponseEntity.ok().body(reviewService.dislike(reviewLikeId));
    }

    // 리뷰 좋아요 수 조회
    @GetMapping("/review/like/{reviewId}")
    public ResponseEntity<?> getLikeCount(@PathVariable Long reviewId) {
        return ResponseEntity.ok().body(reviewService.getLikeCount(reviewId));
    }

}
