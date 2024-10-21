package com.project.doctor_fish_back.dto.user.response.comment;

import com.project.doctor_fish_back.entity.Comment;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class RespGetCommentListDto {
    private List<Comment> comments;
    private Long commentCount;
}
