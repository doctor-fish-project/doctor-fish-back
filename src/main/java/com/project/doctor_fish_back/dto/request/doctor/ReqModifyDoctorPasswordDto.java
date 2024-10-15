package com.project.doctor_fish_back.dto.request.doctor;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class ReqModifyDoctorPasswordDto {
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[~!@#$%^&*?])[A-Za-z\\d~!@#$%^&*?]{8,16}$", message = "비밀번호는 8자이상 16자이하의 영대소문, 숫자, 특수문자(~!@#$%^&*?)를 포함하여합니다.")
    private String password;
    private String checkPassword;
}
