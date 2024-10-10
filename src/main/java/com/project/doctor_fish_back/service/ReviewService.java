package com.project.doctor_fish_back.service;

import com.project.doctor_fish_back.dto.request.review.ReqModifyReviewDto;
import com.project.doctor_fish_back.dto.request.review.ReqWriteReviewDto;
import com.project.doctor_fish_back.dto.response.review.RespGetReviewListDto;
import com.project.doctor_fish_back.entity.Reservation;
import com.project.doctor_fish_back.entity.Review;
import com.project.doctor_fish_back.exception.AuthorityException;
import com.project.doctor_fish_back.repository.ReviewMapper;
import com.project.doctor_fish_back.security.principal.PrincipalUser;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    public Boolean writeReview(ReqWriteReviewDto dto) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        reviewMapper.save(dto.toEntity(principalUser.getId()));
        return true;
    }

    public RespGetReviewListDto getReviewsToUser() {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Review> reviews = reviewMapper.getReviewsToUser(principalUser.getId());
        int reviewCount = reviewMapper.getReviewCountByUserId(principalUser.getId());

        return RespGetReviewListDto.builder()
                .reviews(reviews)
                .reviewCount(reviewCount)
                .build();
    }

    public Boolean modifyReview(Long reviewId, ReqModifyReviewDto dto) throws NotFoundException, AuthorityException {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Review review = reviewMapper.findById(reviewId);

        if(review == null) {
            throw new NotFoundException("해당 리뷰를 찾을 수 없습니다.");
        }

        if(review.getUserId() != principalUser.getId()) {
            throw new AuthorityException("권한이 없습니다.");
        }

        reviewMapper.modify(dto.toEntity(reviewId));

        return true;
    }

    public Boolean deleteReview(Long reviewId) throws NotFoundException, AuthorityException {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Review review = reviewMapper.findById(reviewId);

        if(review == null) {
            throw new NotFoundException("해당 리뷰를 찾을 수 없습니다.");
        }

        if(review.getUserId() != principalUser.getId()) {
            throw new AuthorityException("권한이 없습니다.");
        }

        reviewMapper.deleteById(reviewId);

        return true;
    }


}
