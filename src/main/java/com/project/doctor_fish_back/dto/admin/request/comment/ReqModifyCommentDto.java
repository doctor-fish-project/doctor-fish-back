package com.project.doctor_fish_back.dto.admin.request.comment;

import com.project.doctor_fish_back.entity.Comment;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReqModifyCommentDto {
    private Long id;
    @NotBlank(message = "내용을 입력하세요.")
    private String content;

    public Comment toEntity() {
        return Comment.builder()
                .id(id)
                .content(content)
                .build();
    }
}
