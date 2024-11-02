package com.project.doctor_fish_back.repository.user;

import com.project.doctor_fish_back.entity.Doctor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDoctorMapper {
    // 의사 단건 조회
    Doctor findById(Long id);

    // 유저 페이지 의사 전체 조회
    List<Doctor> doctorList();
    Long doctorCount();

}