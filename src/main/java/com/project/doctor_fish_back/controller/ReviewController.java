package com.project.doctor_fish_back.controller;

import com.project.doctor_fish_back.aspect.annotation.ValidAop;
import com.project.doctor_fish_back.dto.request.review.ReqModifyReviewDto;
import com.project.doctor_fish_back.dto.request.review.ReqWriteReviewDto;
import com.project.doctor_fish_back.exception.AuthorityException;
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
        return ResponseEntity.ok().body(reviewService.writeReview(dto));
    }

    // 사용자 내 리뷰 전체 조회
    @GetMapping("/review/me")
    public ResponseEntity<?> getReviewsToUser() {
        return ResponseEntity.ok().body(reviewService.getReviewsToUser());
    }

    // 리뷰 수정
    @ValidAop
    @PutMapping("/review/{reviewId}")
    public ResponseEntity<?> modifyReview(@PathVariable Long reviewId, @Valid @RequestBody ReqModifyReviewDto dto, BindingResult bindingResult) throws NotFoundException, AuthorityException {
        return ResponseEntity.ok().body(reviewService.modifyReview(reviewId, dto));
    }

    // 리뷰 삭제
    @DeleteMapping("/review/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable Long reviewId) throws NotFoundException, AuthorityException {
        return ResponseEntity.ok().body(reviewService.deleteReview(reviewId));
    }

}
