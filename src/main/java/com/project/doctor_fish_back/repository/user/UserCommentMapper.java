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

    // 리뷰 삭제 시 해당 리뷰의 댓글 삭제
    int deleteCommentsByReviewId(Long reviewId);

    // 유저 페이지 리뷰 단건 조회
    Comment findById(Long id);

    // 유저 페이지 리뷰별 전체 댓글 조회
    List<Comment> commentListByReviewId(@Param("reviewId") Long reviewId,
                                        @Param("startIndex") Long startIndex,
                                        @Param("limit") Long limit);
    Long commentCountByReviewId(Long reviewId);

    // 유저 페이지 내가 작성한 댓글 조회
    List<Comment> commentListByUserId(@Param("userId") Long userId,
                                       @Param("startIndex") Long startIndex,
                                       @Param("limit") Long limit);
    Long commentCountByUserId(Long userId);
}
