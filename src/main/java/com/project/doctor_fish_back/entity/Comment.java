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
public class Comment {
    private Long id;
    private Long reviewId;
    private Long userId;
    private String content;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
}
