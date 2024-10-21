package com.project.doctor_fish_back.dto.admin.response.auth;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RespSigninDto {
    private String expireDate;
    private String accessToken;
}
