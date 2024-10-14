package com.project.doctor_fish_back.dto.request.doctor;

import com.project.doctor_fish_back.entity.Doctor;
import lombok.Data;

@Data
public class ReqRegisterDoctorDto {
    private String name;
    private String departName;
    private String img;
    private String comment;
    private String record;

    public Doctor toEntity(Long departId) {
        return Doctor.builder()
                .name(name)
                .departId(departId)
                .img(img)
                .comment(comment)
                .record(record)
                .build();
    }
}