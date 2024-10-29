package com.project.doctor_fish_back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Review {
    private Long id;
    private Long userId;
    private String img;
    private String content;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;

    private Integer commentCount;

    private Integer likeCount;
    private Integer isLike;

    private String userName;
    private String userImg;
}
