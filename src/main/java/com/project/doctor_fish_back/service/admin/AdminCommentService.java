package com.project.doctor_fish_back.service.admin;

import com.project.doctor_fish_back.dto.admin.request.comment.ReqModifyCommentDto;
import com.project.doctor_fish_back.dto.admin.request.comment.ReqRegisterCommentDto;
import com.project.doctor_fish_back.dto.admin.request.reservation.ReqPageAndLimitDto;
import com.project.doctor_fish_back.dto.admin.response.comment.RespGetCommentListDto;
import com.project.doctor_fish_back.entity.Comment;
import com.project.doctor_fish_back.exception.ExecutionException;
import com.project.doctor_fish_back.repository.admin.AdminCommentMapper;
import com.project.doctor_fish_back.security.principal.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminCommentService {

    @Autowired
    private AdminCommentMapper commentMapper;

    public Boolean writeComment(ReqRegisterCommentDto dto) {
        try {
            PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            Comment comment = dto.toEntity(principalUser.getId());

            commentMapper.save(comment);
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }

        return true;
    }

    public RespGetCommentListDto getComments(Long reviewId, ReqPageAndLimitDto dto) {
        try {
            Long startIndex = (dto.getPage() - 1) * dto.getLimit();
            List<Comment> comments = commentMapper.commentListByReviewId(reviewId, startIndex, dto.getLimit());
            Long commentCount = commentMapper.commentsCountByReviewId(reviewId);

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
            System.out.println(dto.toEntity());
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
