package com.project.doctor_fish_back.dto.admin.request.comment;

import com.project.doctor_fish_back.entity.Comment;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReqRegisterCommentDto {
    private Long reviewId;
    @NotBlank(message = "내용을 입력하세요.")
    private String content;

    public Comment toEntity(Long userId) {
        return Comment.builder()
                .reviewId(reviewId)
                .userId(userId)
                .content(content)
                .build();
    }
}
