package com.project.doctor_fish_back.dto.request.auth;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReqAdminSigninDto {
    @NotBlank(message = "아이디는 공백일 수 없습니다.")
    private String userName;
    @NotBlank(message = "비밀번호는 공백일 수 없습니다.")
    private String password;
}
