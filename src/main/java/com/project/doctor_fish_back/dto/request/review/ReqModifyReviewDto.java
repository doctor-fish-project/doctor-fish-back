package com.project.doctor_fish_back.dto.request.review;

import com.project.doctor_fish_back.entity.Review;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReqModifyReviewDto {
    private String img;
    @NotBlank(message = "내용을 입력하세요.")
    private String content;

    public Review toEntity(Long reviewId) {
        return Review.builder()
                .id(reviewId)
                .img(img)
                .content(content)
                .build();
    }
}
