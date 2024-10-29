package com.project.doctor_fish_back.dto.user.request.review;

import com.project.doctor_fish_back.entity.Review;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReqWriteReviewDto {
    private Long reservationId;
    private String imgList;
    @NotBlank(message = "내용을 입력하세요.")
    private String content;

    public Review toEntity(Long userId) {
        return Review.builder()
                .userId(userId)
                .reservationId(reservationId)
                .img(imgList)
                .content(content)
                .build();
    }
}
