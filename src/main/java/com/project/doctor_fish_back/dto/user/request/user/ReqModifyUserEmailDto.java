package com.project.doctor_fish_back.dto.user.request.user;

import com.project.doctor_fish_back.entity.User;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class ReqModifyUserEmailDto {
    @NotBlank(message = "이메일은 공백일 수 없습니다.")
    @Email(message = "이메일 형식이어야 합니다.")
    private String email;

    public User toEntity(Long userId) {
        return User.builder()
                .id(userId)
                .email(email)
                .build();
    }
}
