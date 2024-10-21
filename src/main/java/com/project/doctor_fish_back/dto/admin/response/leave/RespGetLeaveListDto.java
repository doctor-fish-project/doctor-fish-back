package com.project.doctor_fish_back.dto.admin.response.leave;

import com.project.doctor_fish_back.entity.Leave;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class RespGetLeaveListDto {
    private List<Leave> leaves;
    private Long leaveCount;
}
