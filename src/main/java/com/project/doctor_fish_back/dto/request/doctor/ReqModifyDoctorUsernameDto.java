package com.project.doctor_fish_back.dto.request.doctor;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class ReqModifyDoctorUsernameDto {
    @NotBlank(message = "아이디는 공백일 수 없습니다.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "아이디는 영어여야합니다.")
    private String username;
}
