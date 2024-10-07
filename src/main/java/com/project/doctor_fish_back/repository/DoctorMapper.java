package com.project.doctor_fish_back.repository;

import com.project.doctor_fish_back.entity.Doctor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DoctorMapper {
    int save(Doctor doctor);
}