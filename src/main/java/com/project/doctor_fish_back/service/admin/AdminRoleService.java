package com.project.doctor_fish_back.service.admin;

import com.project.doctor_fish_back.dto.admin.response.Role.RespRolesDto;
import com.project.doctor_fish_back.entity.Role;
import com.project.doctor_fish_back.exception.ExecutionException;
import com.project.doctor_fish_back.repository.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminRoleService {

    @Autowired
    private RoleMapper RoleMapper;

    public List<RespRolesDto> getRoles() {
        try {
            List<Role> roles = RoleMapper.roleList();
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
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }
}
