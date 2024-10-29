package com.project.doctor_fish_back.dto.admin.response.user;

import com.project.doctor_fish_back.entity.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Data
public class RespMyInfoDto {
    private Long id;
    private String email;
    private String name;
    private String phoneNumber;
    private String img;
    private int emailValid;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
    private String record;
    private String comment;
    private Set<Role> roles;
}
