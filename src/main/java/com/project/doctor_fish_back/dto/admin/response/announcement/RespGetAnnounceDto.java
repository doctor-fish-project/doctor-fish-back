package com.project.doctor_fish_back.dto.admin.response.announcement;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class RespGetAnnounceDto {
    private Long id;
    private Long userId;
    private String title;
    private String content;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
}
