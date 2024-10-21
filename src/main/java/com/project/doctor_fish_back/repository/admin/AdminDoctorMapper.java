package com.project.doctor_fish_back.repository.admin;

import com.project.doctor_fish_back.entity.Doctor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminDoctorMapper {
    int save(Doctor doctor);
    List<Doctor> getAll();
    Long getCountAll();
    int modify(Doctor doctor);
    Doctor findById(Long id);
    int deleteById(Long id);
}