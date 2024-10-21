package com.project.doctor_fish_back.dto.admin.response.user;

import com.project.doctor_fish_back.entity.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class RespGetUserListDto {
    private List<User> users;
    private Long userCount;
}
