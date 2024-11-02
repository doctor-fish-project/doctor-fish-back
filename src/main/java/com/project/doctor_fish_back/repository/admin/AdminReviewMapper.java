package com.project.doctor_fish_back.repository.admin;

import com.project.doctor_fish_back.entity.Review;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminReviewMapper {
    int deleteById(Long id);

    Review findById(@Param("reviewId") Long reviewId,
                    @Param("userId") Long userId);

    // 관리자 페이지 리뷰 전체 조회
    List<Review> reviewList(@Param("startIndex") Long startIndex,
                            @Param("limit") Long limit,
                            @Param("searchText") String searchText);
    Long reviewCount(String searchText);
}
