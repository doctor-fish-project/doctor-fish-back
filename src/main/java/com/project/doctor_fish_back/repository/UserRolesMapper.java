package com.project.doctor_fish_back.repository;

import com.project.doctor_fish_back.entity.UserRoles;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRolesMapper {
    int save(UserRoles userRoles);
    int deleteByUserId(Long userId);
    int findRoleIdByUserId(Long userId);
}
