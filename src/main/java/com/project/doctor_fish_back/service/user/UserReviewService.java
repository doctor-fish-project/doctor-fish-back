package com.project.doctor_fish_back.service.user;

import com.project.doctor_fish_back.dto.admin.request.reservation.ReqPageAndLimitDto;
import com.project.doctor_fish_back.dto.user.response.review.RespGetReviewListDto;
import com.project.doctor_fish_back.dto.user.response.review.RespReviewDto;
import com.project.doctor_fish_back.dto.user.request.review.ReqModifyReviewDto;
import com.project.doctor_fish_back.dto.user.request.review.ReqWriteReviewDto;
import com.project.doctor_fish_back.entity.Review;
import com.project.doctor_fish_back.entity.ReviewLike;
import com.project.doctor_fish_back.exception.ExecutionException;
import com.project.doctor_fish_back.exception.ReviewLikeException;
import com.project.doctor_fish_back.repository.user.UserCommentMapper;
import com.project.doctor_fish_back.repository.user.UserReservationMapper;
import com.project.doctor_fish_back.repository.user.UserReviewLikeMapper;
import com.project.doctor_fish_back.repository.user.UserReviewMapper;
import com.project.doctor_fish_back.security.principal.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserReviewService {

    @Autowired
    private UserReviewMapper reviewMapper;
    @Autowired
    private UserReviewLikeMapper reviewLikeMapper;
    @Autowired
    private UserReservationMapper reservationMapper;
    @Autowired
    private UserCommentMapper userCommentMapper;

    public Boolean writeReview(ReqWriteReviewDto dto) {
        try {
            PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            reviewMapper.save(dto.toEntity(principalUser.getId()));
            reservationMapper.modifyReviewStatusById(dto.getReservationId());
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
        return true;
    }

    public Boolean modifyReview(Long reviewId, ReqModifyReviewDto dto) {
        try {
            reviewMapper.modify(dto.toEntity(reviewId));
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
        return true;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteReview(Long reviewId) {
        try {
            PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Review review = reviewMapper.findById(principalUser.getId(), reviewId);
            reservationMapper.modifyReviewStatusById(review.getReservationId());
            reviewLikeMapper.deleteReviewsByReviewId(reviewId);
            userCommentMapper.deleteCommentsByReviewId(reviewId);
            reviewMapper.deleteById(reviewId);
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
        return true;
    }

    public RespGetReviewListDto getReviewsByUserId(ReqPageAndLimitDto dto) {
        try {
            PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Long startIndex = (dto.getPage() - 1) * dto.getLimit();
            List<Review> reviews = reviewMapper.reviewListByUserId(principalUser.getId(), startIndex, dto.getLimit());
            Long reviewCount = reviewMapper.reviewCountByUserId(principalUser.getId());

            return RespGetReviewListDto.builder()
                    .reviews(reviews)
                    .reviewCount(reviewCount)
                    .build();
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }

    public RespGetReviewListDto getReviews(ReqPageAndLimitDto dto) {
        try {
            Long userId = null;
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (principal instanceof PrincipalUser) {
                userId = ((PrincipalUser) principal).getId();
            }
            Long startIndex = (dto.getPage() - 1) * dto.getLimit();
            List<Review> reviews = reviewMapper.reviewList(userId, startIndex, dto.getLimit());
            Long reviewCount = reviewMapper.reviewCount();

            return RespGetReviewListDto.builder()
                    .reviews(reviews)
                    .reviewCount(reviewCount)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }

    public Boolean like(Long reviewId) throws ReviewLikeException {
        try {
            PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Long userId = principalUser.getId();

            ReviewLike reviewLike = reviewLikeMapper.findByReviewIdAndUserId(reviewId, userId);

            if(reviewLike != null) {
                throw new ReviewLikeException("이미 좋아요한 리뷰입니다.");
            }

            ReviewLike rl = ReviewLike.builder()
                    .reviewId(reviewId)
                    .userId(userId)
                    .build();

            reviewLikeMapper.save(rl);
        } catch (ReviewLikeException e) {
            throw new ReviewLikeException(e.getMessage());
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
        return true;
    }

    public Boolean dislike(Long reviewId) {
        try {
            PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            reviewLikeMapper.deleteById(reviewId, principalUser.getId());
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
        return true;
    }

    public RespReviewDto getReviewById(Long reviewId) {
        try {
            Long userId = null;
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (principal instanceof PrincipalUser) {
                userId = ((PrincipalUser) principal).getId();
            }
            Review review = reviewMapper.findById(userId, reviewId);

            return RespReviewDto.builder()
                    .id(review.getId())
                    .userId(review.getUserId())
                    .img(review.getImg())
                    .content(review.getContent())
                    .registerDate(review.getRegisterDate())
                    .updateDate(review.getUpdateDate())
                    .likeCount(review.getLikeCount())
                    .isLike(review.getIsLike())
                    .userName(review.getUserName())
                    .userImg(review.getUserImg())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }

}
