package com.project.doctor_fish_back.dto.response.review;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RespReviewLikeInfoDto {
    private Long reviewLikeId;
    private int likeCount;
}
