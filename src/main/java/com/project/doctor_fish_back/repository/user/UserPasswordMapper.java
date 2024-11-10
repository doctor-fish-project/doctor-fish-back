package com.project.doctor_fish_back.repository.user;

import com.project.doctor_fish_back.entity.Password;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserPasswordMapper {
    int save(Password password);
    int modifyStatusByEmail(String email);
    int deleteByEmail(String email);

    Password findByEmail(String email);

}
