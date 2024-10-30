package com.project.doctor_fish_back.entity;

import com.project.doctor_fish_back.dto.user.response.alarm.RespAlarmDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AlarmInsert {
    private Long id;
    private Long typeId;
    private Long alarmId;
    private Long messageId;

    private String message;

    public RespAlarmDto toDto() {
        return RespAlarmDto.builder()
                .id(id)
                .typeId(typeId)
                .alarmId(alarmId)
                .messageId(messageId)
                .message(message)
                .build();
    }
}
