package com.project.doctor_fish_back.dto.changePassword;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RespGetChangePasswordDto {
    private Long id;
    private String email;
    private int status;
}
