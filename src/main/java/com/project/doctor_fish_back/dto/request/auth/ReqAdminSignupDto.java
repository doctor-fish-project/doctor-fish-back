package com.project.doctor_fish_back.dto.request.auth;

import lombok.Data;

@Data
public class ReqAdminSignupDto {
    private String username;
    private String password;
    private String checkPassword;
}
