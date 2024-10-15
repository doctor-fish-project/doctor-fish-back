package com.project.doctor_fish_back.service;

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

    public Boolean writeComment(ReqRegisterCommentDto dto) throws NotFoundException {
        Review review = reviewMapper.findById(dto.getReviewId());

        if(review == null) {
            throw new NotFoundException("해당 리뷰를 찾을 수 없습니다.");
        }

        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Comment comment = dto.toEntity(principalUser.getId());

        commentMapper.save(comment);

        return true;
    }

    public RespGetCommentListDto getComments(Long reviewId) throws NotFoundException {
        Review review = reviewMapper.findById(reviewId);

        if(review == null) {
            throw new NotFoundException("해당 리뷰를 찾을 수 없습니다.");
        }

        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Comment> comments = commentMapper.findAllByReviewId(reviewId);
        Long commentCount = commentMapper.getCommentCountByReviewId(reviewId);

        return RespGetCommentListDto.builder()
                .comments(comments)
                .commentCount(commentCount)
                .build();
    }

    public Boolean modifyComment(ReqModifyCommentDto dto) throws NotFoundException, AuthorityException {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Comment comment = commentMapper.findById(dto.getId());

        if(comment == null) {
            throw new NotFoundException("해당 댓글을 찾을 수 없습니다.");
        }

        if(comment.getUserId() != principalUser.getId()) {
            throw new AuthorityException("권한이 없습니다.");
        }

        commentMapper.modifyById(dto.toEntity());

        return true;
    }

    public Boolean deleteComment(Long commentId) throws NotFoundException, AuthorityException {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Comment comment = commentMapper.findById(commentId);

        if(comment == null) {
            throw new NotFoundException("해당 댓글을 찾을 수 없습니다.");
        }

        if(comment.getUserId() != principalUser.getId()) {
            throw new AuthorityException("권한이 없습니다.");
        }

        commentMapper.deleteById(commentId);

        return true;
    }

}
