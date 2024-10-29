package com.project.doctor_fish_back.repository.admin;

import com.project.doctor_fish_back.entity.Review;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminReviewMapper {
    List<Review> getReviewAllByLimit(Long startIndex, Long limit, @Param("searchText") String searchText);
    Long getCountReviews(String searchText);

    Review findById(Long id);
}
