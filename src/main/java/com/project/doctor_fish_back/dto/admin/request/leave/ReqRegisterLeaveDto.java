package com.project.doctor_fish_back.dto.admin.request.leave;

import com.project.doctor_fish_back.entity.Leave;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReqRegisterLeaveDto {
    private Long doctorId;
    private LocalDateTime leaveDate;
    private LocalDateTime endDate;

    public Leave toEntity() {
        return Leave.builder()
                .doctorId(doctorId)
                .leaveDate(leaveDate)
                .endDate(endDate)
                .build();
    }
}
