package com.project.doctor_fish_back.repository.user;

import com.project.doctor_fish_back.entity.UserRoles;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserUserRolesMapper {
    int save(UserRoles userRoles);
    int deleteByUserId(Long userId);
    int findRoleIdByUserId(Long userId);
}
