package com.project.doctor_fish_back.repository.admin;

import com.project.doctor_fish_back.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminCommentMapper {
    // 관리자 페이지 댓글 작성, 수정, 삭제
    int save(Comment comment);
    int modifyById(Comment comment);
    int deleteById(Long id);

    // 관리자 페이지 댓글 단건 조회
    Comment findById(Long id);

    // 관리자 페이지 리뷰별 댓글 조회
    List<Comment> getCommentsByReviewId(Long reviewId);

    // 관리자 페이지 리뷰별 댓글 수 조회
    Long getCountCommentsByReviewId(Long reviewId);
}
