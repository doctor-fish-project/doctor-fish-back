package com.project.doctor_fish_back.repository.user;

import com.project.doctor_fish_back.entity.ReviewLike;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserReviewLikeMapper {
    int save(ReviewLike reviewLike);
    int deleteById(@Param("reviewId") Long reviewId,
                    @Param("userId")  Long userId);
    ReviewLike findById(Long id);
    Long getLikeCountByReviewId(Long reviewId);
    ReviewLike findByReviewIdAndUserId(
            @Param("reviewId") Long reviewId,
            @Param("userId")  Long userId);
}
