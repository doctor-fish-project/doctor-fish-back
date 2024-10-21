package com.project.doctor_fish_back.dto.admin.request.auth;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReqAdminSigninDto {
    @NotBlank(message = "아이디를 입력해 주세요.")
    private String username;
    @NotBlank(message = "비밀번호를 입력해 주세요.")
    private String password;
}
