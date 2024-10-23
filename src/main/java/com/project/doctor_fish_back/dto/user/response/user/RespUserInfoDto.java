package com.project.doctor_fish_back.dto.user.response.user;

import com.project.doctor_fish_back.entity.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Data
public class RespUserInfoDto {
    private Long id;
    private String email;
    private String name;
    private String phoneNumber;
    private String img;
    private int emailValid;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
    private Set<Role> roles;
}
