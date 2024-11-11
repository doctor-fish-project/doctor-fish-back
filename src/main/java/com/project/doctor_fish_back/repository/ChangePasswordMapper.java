package com.project.doctor_fish_back.repository;

import com.project.doctor_fish_back.entity.ChangePassword;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChangePasswordMapper {
    int save(ChangePassword changePassword);
    int modifyStatusByEmail(String email);
    ChangePassword findByEmail(String email);
    ChangePassword findByUserId(Long userId);
}
