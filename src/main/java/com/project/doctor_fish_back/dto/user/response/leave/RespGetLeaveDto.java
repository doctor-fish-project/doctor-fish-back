package com.project.doctor_fish_back.dto.user.response.leave;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class RespGetLeaveDto {
    private Long id;
    private Long doctorId;
    private int status;
    private LocalDateTime leaveDate;
    private LocalDateTime endDate;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;

    private String userName;
    private String userImg;
    private String departName;
}
