package com.project.doctor_fish_back.dto.user.request.comment;

import com.project.doctor_fish_back.entity.Comment;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReqModifyCommentDto {
    private Long commentId;
    private Long reviewId;
    @NotBlank(message = "내용을 입력하세요.")
    private String content;

    public Comment toEntity() {
        return Comment.builder()
                .id(commentId)
                .content(content)
                .build();
    }
}
