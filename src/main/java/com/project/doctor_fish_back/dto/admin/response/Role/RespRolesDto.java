package com.project.doctor_fish_back.dto.admin.response.Role;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RespRolesDto {
    private Long id;
    private String name;
    private String position;
 }
