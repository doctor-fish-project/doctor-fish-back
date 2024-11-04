package com.project.doctor_fish_back.dto.admin.response.reservation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespYearDto {
    private Long id;
    private int year;
}
