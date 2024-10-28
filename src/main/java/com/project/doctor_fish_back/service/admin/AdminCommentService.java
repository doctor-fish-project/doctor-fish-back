package com.project.doctor_fish_back.service.admin;

import com.project.doctor_fish_back.aspect.annotation.AuthorityAop;
import com.project.doctor_fish_back.aspect.annotation.NotFoundAop;
import com.project.doctor_fish_back.dto.admin.request.comment.ReqModifyCommentDto;
import com.project.doctor_fish_back.dto.admin.request.comment.ReqRegisterCommentDto;
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

    @NotFoundAop
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

    @NotFoundAop
    public RespGetCommentListDto getComments(Long reviewId) {
        try {
            List<Comment> comments = commentMapper.getCommentsByReviewId(reviewId);
            Long commentCount = commentMapper.getCountCommentsByReviewId(reviewId);

            return RespGetCommentListDto.builder()
                    .comments(comments)
                    .commentCount(commentCount)
                    .build();
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }

    @NotFoundAop
    @AuthorityAop
    public Boolean modifyComment(ReqModifyCommentDto dto) {
        try {
            commentMapper.modifyById(dto.toEntity());
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
        return true;
    }

    @NotFoundAop
    @AuthorityAop
    public Boolean deleteComment(Long commentId) {
        try {
            commentMapper.deleteById(commentId);
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
        return true;
    }

}
