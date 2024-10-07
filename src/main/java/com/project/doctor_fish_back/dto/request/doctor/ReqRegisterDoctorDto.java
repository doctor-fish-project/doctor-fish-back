package com.project.doctor_fish_back.dto.request.doctor;

import com.project.doctor_fish_back.entity.Doctor;
import lombok.Data;

@Data
public class ReqRegisterDoctorDto {
    private String name;
    private String depart;

    public Doctor toEntity() {
        return Doctor.builder()
                .name(name)
                .depart(depart)
                .build();
    }
}