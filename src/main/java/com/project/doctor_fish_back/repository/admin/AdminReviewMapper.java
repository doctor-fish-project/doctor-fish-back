package com.project.doctor_fish_back.repository.admin;

import com.project.doctor_fish_back.entity.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminReviewMapper {
    List<Review> getReviewAllByLimit(Long startIndex, Long limit);
    Long getCountReviews();

    Review findById(Long id);

    List<Review> getBySearch(String searchText);
    Long getCountBySearch(String searchText);
}
