package com.project.doctor_fish_back.dto.user.response.alarm;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespAlarmDto {
    private Long alarmId;
    private Long userId;
    private Long announceId;
    private Long messageId;
    private String message;
    private int status;
}
