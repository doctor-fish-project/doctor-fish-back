package com.project.doctor_fish_back.repository;

import com.project.doctor_fish_back.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findById(Long id);
    User findByEmail(String email);
    int save(User user);
    int modifyEmailValidByEmail(String email);
    int deleteById(Long id);
}