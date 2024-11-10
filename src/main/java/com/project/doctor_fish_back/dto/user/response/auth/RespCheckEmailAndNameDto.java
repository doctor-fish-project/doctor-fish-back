package com.project.doctor_fish_back.dto.user.response.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespCheckEmailAndNameDto {
    private String email;
    private int status;
}
