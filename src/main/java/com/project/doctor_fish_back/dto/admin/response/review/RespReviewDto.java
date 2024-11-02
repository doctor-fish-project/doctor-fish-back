package com.project.doctor_fish_back.dto.admin.response.review;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class RespReviewDto {
    private Long id;
    private Long userId;
    private String img;
    private String content;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;

    private Integer likeCount;
    private Integer isLike;

    private String userName;
    private String userImg;
}
