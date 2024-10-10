package com.project.doctor_fish_back.repository;

import com.project.doctor_fish_back.entity.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    int save(Review review);
    List<Review> getReviewsToUser(Long userId);
    int getReviewCountByUserId(Long userId);
    Review findById(Long id);
    int modify(Review review);
    int deleteById(Long id);
}
