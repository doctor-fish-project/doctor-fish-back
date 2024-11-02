package com.project.doctor_fish_back.service.admin;

import com.project.doctor_fish_back.dto.admin.request.reservation.ReqPageAndLimitDto;
import com.project.doctor_fish_back.dto.admin.response.review.RespGetReviewListDto;
import com.project.doctor_fish_back.dto.admin.response.review.RespReviewDto;
import com.project.doctor_fish_back.dto.admin.response.review.RespReviewLikeInfoDto;
import com.project.doctor_fish_back.entity.Review;
import com.project.doctor_fish_back.entity.ReviewLike;
import com.project.doctor_fish_back.exception.ExecutionException;
import com.project.doctor_fish_back.exception.ReviewLikeException;
import com.project.doctor_fish_back.repository.admin.AdminReviewLikeMapper;
import com.project.doctor_fish_back.repository.admin.AdminReviewMapper;
import com.project.doctor_fish_back.repository.user.UserCommentMapper;
import com.project.doctor_fish_back.repository.user.UserReviewLikeMapper;
import com.project.doctor_fish_back.security.principal.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminReviewService {

    @Autowired
    private AdminReviewMapper reviewMapper;

    @Autowired
    private UserCommentMapper userCommentMapper;

    @Autowired
    private UserReviewLikeMapper userReviewLikeMapper;

    @Autowired
    private AdminReviewLikeMapper reviewLikeMapper;

    public RespReviewDto getReview(Long reviewId) {
        try {
            PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Review review = reviewMapper.findById(reviewId, principalUser.getId());
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

    public RespGetReviewListDto getReviewAllByLimit(ReqPageAndLimitDto dto, String searchText) {
        try {
            Long startIndex = (dto.getPage() - 1) * dto.getLimit();
            List<Review> reviews = reviewMapper.reviewList(startIndex, dto.getLimit(), searchText);
            Long reviewCount = reviewMapper.reviewCount(searchText);

            return RespGetReviewListDto.builder()
                    .reviews(reviews)
                    .reviewCount(reviewCount)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }

    public Boolean deleteReview(Long reviewId) {
        try {
            userCommentMapper.deleteCommentsByReviewId(reviewId);
            userReviewLikeMapper.deleteReviewsByReviewId(reviewId);
            reviewMapper.deleteById(reviewId);
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
        return true;
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
            e.printStackTrace();
            throw new ReviewLikeException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExecutionException("실행 도중 오류 발생");
        }

        return true;
    }

    public Boolean dislike(Long reviewId) {
        try {
            PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            reviewLikeMapper.deleteByReviewIdAndUserId(reviewId, principalUser.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExecutionException("실행 도중 오류 발생");
        }
        return true;
    }

    public RespReviewLikeInfoDto getLikeCount(Long reviewId) {
        try {
            PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Long userId = principalUser.getId();

            ReviewLike reviewLike = reviewLikeMapper.findByReviewIdAndUserId(reviewId, userId);
            Long likeCount = reviewLikeMapper.likeCountByReviewId(reviewId);

            return RespReviewLikeInfoDto.builder()
                    .reviewLikeId(reviewLike == null ? 0 : reviewLike.getId())
                    .likeCount(likeCount)
                    .build();
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }

}
