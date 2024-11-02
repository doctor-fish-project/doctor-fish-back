package com.project.doctor_fish_back.repository;

import com.project.doctor_fish_back.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    int save(Role role);

    Role findByPosition(String position);

    List<Role> roleList();
}
