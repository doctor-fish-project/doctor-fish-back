package com.project.doctor_fish_back.repository.admin;

import com.project.doctor_fish_back.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminUserMapper {
    int save(User user);
    int deleteById(Long id);
    int modify(User user);
    int modifyEmail(User user);
    int modifyPassword(User user);
    int modifyEmailValidById(Long id);

    User findById(Long id);
    User findByEmail(String email);
    User findByPhoneNumber(String phoneNumber);

    // 관리자 페이지 회원 전체 조회
    List<User> getUsers(@Param("startIndex") Long startIndex, @Param("limit") Long limit);
    Long getCountUsers();

    // 관리자 페이지 회원 검색 조회
    List<User> getUsersBySearch(String searchText);
    Long getCountUsersBySearch(String searchText);

}