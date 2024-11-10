package com.project.doctor_fish_back.dto.user.request.auth;

import com.project.doctor_fish_back.entity.Password;
import lombok.Data;

@Data
public class ReqCheckEmailAndNameDto {
    private String email;
    private String name;

    public Password toEntity() {
        return Password.builder()
                .email(email)
                .build();
    }
}
