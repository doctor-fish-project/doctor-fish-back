package com.project.doctor_fish_back.dto.user.request.auth;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class ReqSigninDto {
    @NotBlank(message = "이메일을 입력해 주세요.")
    @Email(message = "이메일 형식이어야 합니다.")
    private String email;
    @NotBlank(message = "비밀번호를 입력해 주세요.")
    private String password;
}