package com.project.doctor_fish_back.service;

import com.project.doctor_fish_back.aspect.annotation.AuthorityAop;
import com.project.doctor_fish_back.aspect.annotation.NotFoundAop;
import com.project.doctor_fish_back.dto.request.comment.ReqModifyCommentDto;
import com.project.doctor_fish_back.dto.request.comment.ReqRegisterCommentDto;
import com.project.doctor_fish_back.dto.response.comment.RespGetCommentListDto;
import com.project.doctor_fish_back.entity.Comment;
import com.project.doctor_fish_back.entity.Review;
import com.project.doctor_fish_back.exception.AuthorityException;
import com.project.doctor_fish_back.repository.CommentMapper;
import com.project.doctor_fish_back.repository.ReviewMapper;
import com.project.doctor_fish_back.security.principal.PrincipalUser;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ReviewMapper reviewMapper;

    @NotFoundAop
    public Boolean writeComment(ReqRegisterCommentDto dto) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Comment comment = dto.toEntity(principalUser.getId());

        commentMapper.save(comment);

        return true;
    }

    @NotFoundAop
    public RespGetCommentListDto getComments(Long reviewId) {
        List<Comment> comments = commentMapper.findAllByReviewId(reviewId);
        Long commentCount = commentMapper.getCommentCountByReviewId(reviewId);

        return RespGetCommentListDto.builder()
                .comments(comments)
                .commentCount(commentCount)
                .build();
    }

    @NotFoundAop
    @AuthorityAop
    public Boolean modifyComment(ReqModifyCommentDto dto) {
        commentMapper.modifyById(dto.toEntity());
        return true;
    }

    @NotFoundAop
    @AuthorityAop
    public Boolean deleteComment(Long commentId) {
        commentMapper.deleteById(commentId);
        return true;
    }

}
