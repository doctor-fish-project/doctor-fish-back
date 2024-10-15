package com.project.doctor_fish_back.dto.request.doctor;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReqModifyDoctorUsernameDto {
    @NotBlank(message = "아이디는 공백일 수 없습니다.")
    private String username;
}
