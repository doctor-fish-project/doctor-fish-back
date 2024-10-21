package com.project.doctor_fish_back.service.user;

import com.project.doctor_fish_back.aspect.annotation.AuthorityAop;
import com.project.doctor_fish_back.aspect.annotation.NotFoundAop;
import com.project.doctor_fish_back.dto.user.response.review.RespGetReviewListDto;
import com.project.doctor_fish_back.dto.user.response.review.RespReviewLikeInfoDto;
import com.project.doctor_fish_back.dto.user.request.review.ReqModifyReviewDto;
import com.project.doctor_fish_back.dto.user.request.review.ReqWriteReviewDto;
import com.project.doctor_fish_back.entity.Review;
import com.project.doctor_fish_back.entity.ReviewLike;
import com.project.doctor_fish_back.exception.ReviewLikeException;
import com.project.doctor_fish_back.repository.user.UserReviewLikeMapper;
import com.project.doctor_fish_back.repository.user.UserReviewMapper;
import com.project.doctor_fish_back.security.principal.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserReviewService {

    @Autowired
    private UserReviewMapper reviewMapper;

    @Autowired
    private UserReviewLikeMapper reviewLikeMapper;

    public Boolean writeReview(ReqWriteReviewDto dto) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        reviewMapper.save(dto.toEntity(principalUser.getId()));
        return true;
    }

    public RespGetReviewListDto getReviews() {
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

    @NotFoundAop
    @AuthorityAop
    public Boolean modifyReview(Long reviewId, ReqModifyReviewDto dto) {
        reviewMapper.modify(dto.toEntity(reviewId));
        return true;
    }

    @NotFoundAop
    @AuthorityAop
    public Boolean deleteReview(Long reviewId) {
        reviewMapper.deleteById(reviewId);
        return true;
    }

    @NotFoundAop
    public Boolean like(Long reviewId) throws ReviewLikeException {
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

        return true;
    }

    @NotFoundAop
    @AuthorityAop
    public Boolean dislike(Long reviewLikeId) {
        reviewLikeMapper.deleteById(reviewLikeId);
        return true;
    }

    @NotFoundAop
    public RespReviewLikeInfoDto getLikeCount(Long reviewId) {
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
