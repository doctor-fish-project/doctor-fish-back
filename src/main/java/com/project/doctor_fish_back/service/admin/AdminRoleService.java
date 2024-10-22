package com.project.doctor_fish_back.service.admin;

import com.project.doctor_fish_back.dto.admin.response.Role.RespRolesDto;
import com.project.doctor_fish_back.entity.Role;
import com.project.doctor_fish_back.repository.admin.AdminRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminRoleService {

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    public List<RespRolesDto> getRoles() {
        List<Role> roles = adminRoleMapper.getRoles();
        List<RespRolesDto> dtos = new ArrayList<>();

        for(Role role : roles) {
            RespRolesDto dto = RespRolesDto.builder()
                    .id(role.getId())
                    .name(role.getName())
                    .position(role.getPosition())
                    .build();
            dtos.add(dto);
        }
        return dtos;
    }
}
