package com.project.doctor_fish_back.dto.admin.request.leave;

import com.project.doctor_fish_back.entity.Leave;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class ReqRegisterLeaveDto {
    private Long userId;
    @NotBlank(message = "사유를 작성해 주세요")
    private String reason;
    @NotBlank(message = "연차 시작 시간을 입력해주세요")
    private LocalDateTime leaveDate;
    @NotBlank(message = "연차 끝나는 시간을 입력해주세요")
    private LocalDateTime endDate;

    public Leave toEntity() {
        return Leave.builder()
                .userId(userId)
                .reason(reason)
                .leaveDate(leaveDate)
                .endDate(endDate)
                .build();
    }
}
