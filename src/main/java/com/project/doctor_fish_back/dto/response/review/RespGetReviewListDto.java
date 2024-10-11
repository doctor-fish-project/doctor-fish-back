package com.project.doctor_fish_back.dto.response.review;

import com.project.doctor_fish_back.entity.Review;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class RespGetReviewListDto {
    private List<Review> reviews;
    private int reviewCount;
}