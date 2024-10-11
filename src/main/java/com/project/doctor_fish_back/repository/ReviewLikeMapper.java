package com.project.doctor_fish_back.repository;

import com.project.doctor_fish_back.entity.ReviewLike;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReviewLikeMapper {
    int save(ReviewLike reviewLike);
    int deleteById(Long id);
    ReviewLike findById(Long id);
    int getLikeCountByReviewId(Long reviewId);
    ReviewLike findByReviewIdAndUserId(
            @Param("reviewId") Long reviewId,
            @Param("userId")  Long userId);
}
