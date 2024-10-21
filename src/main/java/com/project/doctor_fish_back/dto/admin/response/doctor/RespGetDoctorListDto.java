package com.project.doctor_fish_back.dto.admin.response.doctor;

import com.project.doctor_fish_back.entity.Doctor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class RespGetDoctorListDto {
    private List<Doctor> doctors;
    private Long doctorCount;
}
