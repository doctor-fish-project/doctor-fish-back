package com.project.doctor_fish_back.repository.admin;

import com.project.doctor_fish_back.entity.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminReviewMapper {
    int deleteById(Long id);

    // 관리자 페이지 리뷰 전체 조회
    List<Review> getReviewAllByLimit(Long startIndex, Long limit);
    Long getCountReviews();

    Review findById(Long id);

    List<Review> getBySearch(String searchText);
    Long getCountBySearch(String searchText);
}
