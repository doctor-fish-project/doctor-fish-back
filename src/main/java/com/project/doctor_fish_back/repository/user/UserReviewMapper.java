package com.project.doctor_fish_back.repository.user;

import com.project.doctor_fish_back.entity.Review;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserReviewMapper {
    // 유저 페이지 리뷰 저장, 수정, 삭제
    int save(Review review);
    int modify(Review review);
    int deleteById(Long id);

    // 유저 페이지 사용자의 리뷰 단건 조회
    Review findById(@Param("userId") Long userId,
                    @Param("reviewId") Long reviewId);

    // 유저 페이지 사용자의 리뷰 조회
    List<Review> reviewListByUserId(@Param("userId") Long userId,
                                    @Param("startIndex") Long startIndex,
                                    @Param("limit") Long limit);
    Long reviewCountByUserId(Long userId);

    // 유저 페이지 리뷰 페이지 전체 조회
    List<Review> reviewList(@Param("userId") Long userId,
                            @Param("startIndex") Long startIndex,
                            @Param("limit") Long limit);
    Long reviewCount();

}
