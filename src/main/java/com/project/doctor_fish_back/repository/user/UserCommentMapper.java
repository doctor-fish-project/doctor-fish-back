package com.project.doctor_fish_back.repository.user;

import com.project.doctor_fish_back.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserCommentMapper {
    int save(Comment comment);
    int modifyById(Comment comment);
    int deleteById(Long id);

    Long getCommentCountByReviewId(Long reviewId);

    Comment findById(Long id);

    List<Comment> findAllByReviewId(@Param("reviewId") Long reviewId,
                                    @Param("startIndex") Long startIndex,
                                    @Param("limit") Long limit);

}
