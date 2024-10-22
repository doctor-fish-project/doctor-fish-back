package com.project.doctor_fish_back.dto.admin.response.depart;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RespDepartListDto {
    private Long id;
    private String name;
}
