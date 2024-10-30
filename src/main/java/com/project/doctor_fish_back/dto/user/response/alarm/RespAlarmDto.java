package com.project.doctor_fish_back.dto.user.response.alarm;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespAlarmDto {
    private Long id;
    private Long typeId;
    private Long alarmId;
    private Long messageId;
    private String message;
}
