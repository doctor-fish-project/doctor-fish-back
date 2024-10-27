package com.project.doctor_fish_back.repository.user;

import com.project.doctor_fish_back.entity.Review;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserReviewMapper {
    int save(Review review);
    int modify(Review review);
    int deleteById(Long id);

    Long getReviewCountByUserId(Long userId);
    Long getReviewAllCount();

    List<Review> getReviewsToUser(@Param("userId") Long userId, @Param("startIndex") Long startIndex, @Param("limit") Long limit);
    List<Review> getReviewAll(@Param("userId") Long userId, @Param("startIndex") Long startIndex, @Param("limit") Long limit);

    Review findById(@Param("userId") Long userId, @Param("reviewId") Long reviewId);

}
