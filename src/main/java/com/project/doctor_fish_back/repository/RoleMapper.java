package com.project.doctor_fish_back.repository;

import com.project.doctor_fish_back.entity.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper {
    Role findByPosition(String position);
    int save(Role role);

}
