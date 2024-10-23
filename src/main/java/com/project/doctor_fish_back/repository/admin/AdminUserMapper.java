package com.project.doctor_fish_back.repository.admin;

import com.project.doctor_fish_back.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminUserMapper {
    User findById(Long id);
    User findByEmail(String email);
    User findByPhoneNumber(String phoneNumber);
    int save(User user);
    int modifyEmailValidByEmail(String email);
    int deleteById(Long id);
    int modify(User user);
    int modifyEmail(User user);
    int modifyPassword(User user);
    List<User> getAll(@Param("startIndex") Long startIndex,
                      @Param("limit") Long limit);
    Long getCountAll();
    int modifyEmailValidById(Long id);

    List<User> getBySearch(String searchText);
    Long getCountBySearch(String searchText);

}