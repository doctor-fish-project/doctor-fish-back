package com.project.doctor_fish_back.repository.user;

import com.project.doctor_fish_back.entity.Doctor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDoctorMapper {
    Doctor getDoctorById(Long id);
    List<Doctor> getAll();
    Long getCountAll();
}