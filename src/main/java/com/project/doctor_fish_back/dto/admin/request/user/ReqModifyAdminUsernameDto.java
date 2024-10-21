package com.project.doctor_fish_back.dto.admin.request.user;

import com.project.doctor_fish_back.entity.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class ReqModifyAdminUsernameDto {
    @NotBlank(message = "아이디는 공백일 수 없습니다.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "아이디는 영어여야합니다.")
    private String username;

    public User toEntity(Long id) {
        return User.builder()
                .id(id)
                .email(username)
                .build();
    }
}
