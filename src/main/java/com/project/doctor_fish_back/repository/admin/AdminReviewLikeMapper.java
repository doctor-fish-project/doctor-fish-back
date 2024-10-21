package com.project.doctor_fish_back.repository.admin;

import com.project.doctor_fish_back.entity.ReviewLike;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminReviewLikeMapper {
    int save(ReviewLike reviewLike);
    int deleteById(Long id);
    ReviewLike findById(Long id);
    Long getLikeCountByReviewId(Long reviewId);
    ReviewLike findByReviewIdAndUserId(
            @Param("reviewId") Long reviewId,
            @Param("userId")  Long userId);
}
