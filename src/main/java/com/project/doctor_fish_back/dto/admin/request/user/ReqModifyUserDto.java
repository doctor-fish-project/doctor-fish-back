package com.project.doctor_fish_back.dto.admin.request.user;

import com.project.doctor_fish_back.entity.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReqModifyUserDto {
    @NotBlank(message = "이름은 공백일 수 없습니다.")
    private String name;
    @NotBlank(message = "전화번호는 공백일 수 없습니다.")
    private String phoneNumber;
    private String img;
    private String comment;
    private String record;

    public User toEntity(Long userId) {
        return User.builder()
                .id(userId)
                .name(name)
                .phoneNumber(phoneNumber)
                .img(img)
                .build();
    }
}
