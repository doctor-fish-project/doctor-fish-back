package com.project.doctor_fish_back.repository.user;

import com.project.doctor_fish_back.entity.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserReviewMapper {
    int save(Review review);
    int modify(Review review);
    int deleteById(Long id);

    Long getReviewCountByUserId(Long userId);
    Long getReviewAllCount();

    List<Review> getReviewsToUser(Long userId);
    List<Review> getReviewAll();
    List<Review> getReviewAllByLimit(Long startIndex, Long limit);

    Review findById(Long id);

}
