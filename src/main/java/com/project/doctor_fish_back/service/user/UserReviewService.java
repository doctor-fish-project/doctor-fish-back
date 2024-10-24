package com.project.doctor_fish_back.service.user;

import com.project.doctor_fish_back.aspect.annotation.AuthorityAop;
import com.project.doctor_fish_back.aspect.annotation.NotFoundAop;
import com.project.doctor_fish_back.dto.user.response.review.RespGetReviewListDto;
import com.project.doctor_fish_back.dto.user.response.review.RespReviewDto;
import com.project.doctor_fish_back.dto.user.response.review.RespReviewLikeInfoDto;
import com.project.doctor_fish_back.dto.user.request.review.ReqModifyReviewDto;
import com.project.doctor_fish_back.dto.user.request.review.ReqWriteReviewDto;
import com.project.doctor_fish_back.entity.Review;
import com.project.doctor_fish_back.entity.ReviewLike;
import com.project.doctor_fish_back.exception.ExecutionException;
import com.project.doctor_fish_back.exception.ReviewLikeException;
import com.project.doctor_fish_back.repository.user.UserReservationMapper;
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
    @Autowired
    private UserReservationMapper reservationMapper;

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

    public RespGetReviewListDto getReviews() {
        try {
            PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            List<Review> reviews = reviewMapper.getReviewAll(principalUser.getId());
            Long reviewCount = reviewMapper.getReviewAllCount();

            return RespGetReviewListDto.builder()
                    .reviews(reviews)
                    .reviewCount(reviewCount)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }

    public RespGetReviewListDto getReviewsToUser() {
        try {
            PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            List<Review> reviews = reviewMapper.getReviewsToUser(principalUser.getId());
            Long reviewCount = reviewMapper.getReviewCountByUserId(principalUser.getId());

            return RespGetReviewListDto.builder()
                    .reviews(reviews)
                    .reviewCount(reviewCount)
                    .build();
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }

    @NotFoundAop
    @AuthorityAop
    public Boolean modifyReview(Long reviewId, ReqModifyReviewDto dto) {
        try {
            reviewMapper.modify(dto.toEntity(reviewId));
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
        return true;
    }

    @NotFoundAop
    @AuthorityAop
    public Boolean deleteReview(Long reviewId) {
        try {
            reviewMapper.deleteById(reviewId);
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
        return true;
    }

    @NotFoundAop
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

    @NotFoundAop
    @AuthorityAop
    public Boolean dislike(Long reviewId) {
        try {
            PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            reviewLikeMapper.deleteById(reviewId, principalUser.getId());
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
        return true;
    }

    @NotFoundAop
    public RespReviewLikeInfoDto getLikeCount(Long reviewId) {
        try {
            PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Long userId = principalUser.getId();

            ReviewLike reviewLike = reviewLikeMapper.findByReviewIdAndUserId(reviewId, userId);
            Long likeCount = reviewLikeMapper.getLikeCountByReviewId(reviewId);

            return RespReviewLikeInfoDto.builder()
                    .reviewLikeId(reviewLike == null ? 0 : reviewLike.getId())
                    .likeCount(likeCount)
                    .build();
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }

    public RespReviewDto getReview(Long reviewId) {
        try {
            Review review = reviewMapper.findById(reviewId);

            return RespReviewDto.builder()
                    .id(review.getId())
                    .userId(review.getUserId())
                    .img(review.getImg())
                    .content(review.getContent())
                    .registerDate(review.getRegisterDate())
                    .updateDate(review.getUpdateDate())
                    .userName(review.getUserName())
                    .userImg(review.getUserImg())
                    .build();
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }

}
