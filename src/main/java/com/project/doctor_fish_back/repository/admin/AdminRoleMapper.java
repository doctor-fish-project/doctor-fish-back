package com.project.doctor_fish_back.repository.admin;

import com.project.doctor_fish_back.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminRoleMapper {
    List<Role> getRoles();


}
