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
public class Leave {
    private Long id;
    private Long userId;
    private String reason;
    private int status;
    private LocalDateTime leaveDate;
    private LocalDateTime endDate;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;

    private String userName;
    private String userImg;
    private String departName;
}
