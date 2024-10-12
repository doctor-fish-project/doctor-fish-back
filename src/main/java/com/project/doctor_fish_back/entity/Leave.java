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
    private Long doctorId;
    private int status;
    private LocalDateTime leaveDate;
    private LocalDateTime endDate;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
}
