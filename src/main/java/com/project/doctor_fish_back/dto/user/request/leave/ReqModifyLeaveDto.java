package com.project.doctor_fish_back.dto.user.request.leave;

import com.project.doctor_fish_back.entity.Leave;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReqModifyLeaveDto {
    private Long doctorId;
    private LocalDateTime leaveDate;
    private LocalDateTime endDate;

    public Leave toEntity(Long id) {
        return Leave.builder()
                .id(id)
                .doctorId(doctorId)
                .leaveDate(leaveDate)
                .endDate(endDate)
                .build();
    }
}
