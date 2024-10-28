package com.project.doctor_fish_back.repository.user;

import com.project.doctor_fish_back.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserCommentMapper {
    // 유저 페이지 댓글 작성, 수정, 삭제
    int save(Comment comment);
    int modifyById(Comment comment);
    int deleteById(Long id);

    // 유저 페이지 리뷰별 댓글 수 조회
    Long getCommentCountByReviewId(Long reviewId);

    // 유저 페이지 리뷰 단건 조회
    Comment findById(Long id);

    // 유저 페이지 리뷰 전체 조회
    List<Comment> findAllByReviewId(@Param("reviewId") Long reviewId, @Param("startIndex") Long startIndex, @Param("limit") Long limit);
}
