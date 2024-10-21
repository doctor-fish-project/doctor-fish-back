package com.project.doctor_fish_back.dto.admin.request.doctor;

import com.project.doctor_fish_back.entity.Doctor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReqModifyDoctorDto {
    @NotBlank(message = "이름은 공백일 수 없습니다.")
    private String name;
    @NotBlank(message = "전화번호는 공백일 수 없습니다.")
    private String phoneNumber;
    private String img;
    private String comment;
    private String record;

    public Doctor toEntity(Long id) {
        return Doctor.builder()
                .id(id)
                .comment(comment)
                .record(record)
                .build();
    }
}
