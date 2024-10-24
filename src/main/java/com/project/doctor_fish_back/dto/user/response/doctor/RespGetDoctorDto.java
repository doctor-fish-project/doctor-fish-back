package com.project.doctor_fish_back.dto.user.response.doctor;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RespGetDoctorDto {
    private Long doctorId;
    private String name;
    private String departName;
}
