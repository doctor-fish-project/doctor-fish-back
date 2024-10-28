package com.project.doctor_fish_back.controller.user;

import com.project.doctor_fish_back.aspect.annotation.ValidAop;
import com.project.doctor_fish_back.dto.admin.request.reservation.ReqPageAndLimitDto;
import com.project.doctor_fish_back.dto.user.request.review.ReqModifyReviewDto;
import com.project.doctor_fish_back.dto.user.request.review.ReqWriteReviewDto;
import com.project.doctor_fish_back.exception.ReviewLikeException;
import com.project.doctor_fish_back.service.user.UserReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserReviewController {

    @Autowired
    private UserReviewService reviewService;

    // 리뷰 작성
    @ValidAop
    @PostMapping("/review")
    public ResponseEntity<?> writeReview(@Valid @RequestBody ReqWriteReviewDto dto, BindingResult bindingResult) {
        return ResponseEntity.ok().body(reviewService.writeReview(dto));
    }

    // 리뷰 전체 조회
    @GetMapping("/review/list")
    public ResponseEntity<?> getReviews(ReqPageAndLimitDto dto) {
        return ResponseEntity.ok().body(reviewService.getReviews(dto));
    }

    // 리뷰 단건 조회
    @GetMapping("/review/{reviewId}")
    public ResponseEntity<?> getReview(@PathVariable Long reviewId) {
        return ResponseEntity.ok().body(reviewService.getReviewById(reviewId));
    }

    // 사용자 내 리뷰 전체 조회
    @GetMapping("/review/me")
    public ResponseEntity<?> getReviewsToUser(ReqPageAndLimitDto dto) {
        return ResponseEntity.ok().body(reviewService.getReviewsByUserId(dto));
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
    @DeleteMapping("/review/like/{reviewId}")
    public ResponseEntity<?> dislike(@PathVariable Long reviewId) {
        return ResponseEntity.ok().body(reviewService.dislike(reviewId));
    }

}
