package com.project.doctor_fish_back.dto.admin.request.leave;

import com.project.doctor_fish_back.entity.Leave;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReqModifyLeaveDto {
    private Long userId;
    private LocalDateTime leaveDate;
    private LocalDateTime endDate;

    public Leave toEntity(Long id) {
        return Leave.builder()
                .id(id)
                .userId(userId)
                .leaveDate(leaveDate)
                .endDate(endDate)
                .build();
    }
}
