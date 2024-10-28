package com.project.doctor_fish_back.repository.admin;

import com.project.doctor_fish_back.entity.Doctor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminDoctorMapper {
    int modify(Doctor doctor);
    int save(Doctor doctor);

    Doctor findById(Long id);
    Doctor findByUserId(Long userId);
}