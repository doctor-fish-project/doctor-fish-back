package com.project.doctor_fish_back.entity;

import com.project.doctor_fish_back.dto.user.response.auth.RespCheckEmailAndNameDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Password {
    private Long id;
    private String email;
    private int status;

    public RespCheckEmailAndNameDto toDto() {
        return RespCheckEmailAndNameDto.builder()
                .email(email)
                .status(status)
                .build();
    }
}
