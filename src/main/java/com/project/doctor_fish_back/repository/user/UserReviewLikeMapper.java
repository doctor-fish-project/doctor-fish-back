package com.project.doctor_fish_back.repository.user;

import com.project.doctor_fish_back.entity.ReviewLike;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserReviewLikeMapper {
    // 유저 페이지 리뷰 좋아요, 좋아요 삭제
    int save(ReviewLike reviewLike);
    int deleteById(@Param("reviewId") Long reviewId,
                   @Param("userId")  Long userId);

    // 리뷰 삭제 시 좋아요 기록 삭제
    int deleteReviewsByReviewId(Long reviewId);

    // 리뷰 좋아요 아이디 및 유저 아이디 단건 조회
    ReviewLike findByReviewIdAndUserId(@Param("reviewId") Long reviewId,
                                       @Param("userId")  Long userId);


}
