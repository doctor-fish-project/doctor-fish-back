package com.project.doctor_fish_back.repository;

import com.project.doctor_fish_back.entity.Doctor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DoctorMapper {
    int save(Doctor doctor);
    List<Doctor> getAll();
    Long getCountAll();
    int modify(Doctor doctor);
    Doctor findById(Long id);
}