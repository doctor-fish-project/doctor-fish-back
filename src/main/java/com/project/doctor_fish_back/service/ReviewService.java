package com.project.doctor_fish_back.service;

import com.project.doctor_fish_back.dto.request.review.ReqModifyReviewDto;
import com.project.doctor_fish_back.dto.request.review.ReqWriteReviewDto;
import com.project.doctor_fish_back.dto.response.review.RespGetReviewListDto;
import com.project.doctor_fish_back.dto.response.review.RespReviewLikeInfoDto;
import com.project.doctor_fish_back.entity.Review;
import com.project.doctor_fish_back.entity.ReviewLike;
import com.project.doctor_fish_back.exception.AuthorityException;
import com.project.doctor_fish_back.exception.ReviewLikeException;
import com.project.doctor_fish_back.repository.ReviewLikeMapper;
import com.project.doctor_fish_back.repository.ReviewMapper;
import com.project.doctor_fish_back.security.principal.PrincipalUser;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private ReviewLikeMapper reviewLikeMapper;

    public Boolean writeReview(ReqWriteReviewDto dto) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        reviewMapper.save(dto.toEntity(principalUser.getId()));
        return true;
    }

    public RespGetReviewListDto getReviewAll() {
        List<Review> reviews = reviewMapper.getReviewAll();
        Long reviewCount = reviewMapper.getReviewAllCount();

        return RespGetReviewListDto.builder()
                .reviews(reviews)
                .reviewCount(reviewCount)
                .build();
    }

    public RespGetReviewListDto getReviewsToUser() {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Review> reviews = reviewMapper.getReviewsToUser(principalUser.getId());
        Long reviewCount = reviewMapper.getReviewCountByUserId(principalUser.getId());

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

    public Boolean like(Long reviewId) throws NotFoundException, ReviewLikeException {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = principalUser.getId();

        Review review = reviewMapper.findById(reviewId);
        ReviewLike reviewLike = reviewLikeMapper.findByReviewIdAndUserId(reviewId, userId);

        if(review == null) {
            throw new NotFoundException("해당 리뷰를 찾을 수 없습니다.");
        }

        if(reviewLike != null) {
            throw new ReviewLikeException("이미 좋아요한 리뷰입니다.");
        }

        ReviewLike rl = ReviewLike.builder()
                .reviewId(reviewId)
                .userId(userId)
                .build();

        reviewLikeMapper.save(rl);

        return true;
    }

    public Boolean dislike(Long reviewLikeId) throws NotFoundException, AuthorityException {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ReviewLike reviewLike = reviewLikeMapper.findById(reviewLikeId);

        if(reviewLike == null) {
            throw new NotFoundException("좋아요를 취소할 수 없습니다.");
        }

        if(reviewLike.getUserId() != principalUser.getId()) {
            throw new AuthorityException("권한이 없습니다.");
        }

        reviewLikeMapper.deleteById(reviewLikeId);

        return true;
    }

    public RespReviewLikeInfoDto getLikeCount(Long reviewId) throws NotFoundException {
        Review review = reviewMapper.findById(reviewId);

        if(review == null) {
            throw new NotFoundException("해당 리뷰를 찾을 수 없습니다.");
        }

        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = principalUser.getId();

        ReviewLike reviewLike = reviewLikeMapper.findByReviewIdAndUserId(reviewId, userId);
        Long likeCount = reviewLikeMapper.getLikeCountByReviewId(reviewId);

        return RespReviewLikeInfoDto.builder()
                .reviewLikeId(reviewLike == null ? 0 : reviewLike.getId())
                .likeCount(likeCount)
                .build();
    }

}
