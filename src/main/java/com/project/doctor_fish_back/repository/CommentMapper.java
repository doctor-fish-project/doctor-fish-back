package com.project.doctor_fish_back.repository;

import com.project.doctor_fish_back.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    int save(Comment comment);
    List<Comment> findAllByReviewId(Long reviewId);
    int getCommentCountByReviewId(Long reviewId);
    int modifyById(Comment comment);
    Comment findById(Long id);
    int deleteById(Long id);
}
