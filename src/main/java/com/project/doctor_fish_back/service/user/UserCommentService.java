package com.project.doctor_fish_back.service.user;

import com.project.doctor_fish_back.dto.admin.request.reservation.ReqPageAndLimitDto;
import com.project.doctor_fish_back.dto.user.response.comment.RespGetCommentListDto;
import com.project.doctor_fish_back.dto.user.request.comment.ReqModifyCommentDto;
import com.project.doctor_fish_back.dto.user.request.comment.ReqRegisterCommentDto;
import com.project.doctor_fish_back.entity.Comment;
import com.project.doctor_fish_back.exception.ExecutionException;
import com.project.doctor_fish_back.repository.user.UserCommentMapper;
import com.project.doctor_fish_back.security.principal.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCommentService {

    @Autowired
    private UserCommentMapper commentMapper;

    public Boolean writeComment(ReqRegisterCommentDto dto) {
        try {
            PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Comment comment = dto.toEntity(principalUser.getId());
            commentMapper.save(comment);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExecutionException("실행 도중 오류 발생");
        }

        return true;
    }

    public RespGetCommentListDto getComments(Long reviewId, ReqPageAndLimitDto dto) {
        try {
            Long startIndex = (dto.getPage() - 1) * dto.getLimit();
            List<Comment> comments = commentMapper.commentListByReviewId(reviewId, startIndex, dto.getLimit());
            Long commentCount = commentMapper.commentCountByReviewId(reviewId);

            return RespGetCommentListDto.builder()
                    .comments(comments)
                    .commentCount(commentCount)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }

    public RespGetCommentListDto getCommentsByUserId(ReqPageAndLimitDto dto) {
        try {
            PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Long startIndex = (dto.getPage() - 1) * dto.getLimit();
            List<Comment> comments = commentMapper.commentListByUserId(principalUser.getId(), startIndex, dto.getLimit());
            Long commentCount = commentMapper.commentCountByUserId(principalUser.getId());

            return RespGetCommentListDto.builder()
                    .comments(comments)
                    .commentCount(commentCount)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }

    public Boolean modifyComment(ReqModifyCommentDto dto) {
        try {
            commentMapper.modifyById(dto.toEntity());
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
        return true;
    }

    public Boolean deleteComment(Long commentId) {
        try {
            commentMapper.deleteById(commentId);
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
        return true;
    }

}
