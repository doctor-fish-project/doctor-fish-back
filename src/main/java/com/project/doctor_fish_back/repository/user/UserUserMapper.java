package com.project.doctor_fish_back.repository.user;

import com.project.doctor_fish_back.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserUserMapper {
    // 유저 페이지 가입, 로그인, 수정,
    int save(User user);
    int deleteById(Long id);
    int modify(User user);
    int modifyEmail(User user);
    int modifyPassword(User user);
    int modifyEmailValidByEmail(String email);
    int modifyEmailValidById(Long id);

    User findById(Long id);
    User findByEmail(String email);
    User findByPhoneNumber(String phoneNumber);

}