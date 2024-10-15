package com.project.doctor_fish_back.repository;

import com.project.doctor_fish_back.entity.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    int save(Review review);
    int modify(Review review);
    int deleteById(Long id);

    Long getReviewCountByUserId(Long userId);
    Long getReviewAllCount();

    List<Review> getReviewsToUser(Long userId);
    List<Review> getReviewAll();


    Review findById(Long id);

}
