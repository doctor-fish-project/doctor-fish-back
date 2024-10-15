package com.project.doctor_fish_back.service;

import com.project.doctor_fish_back.dto.response.doctor.RespGetDoctorListDto;
import com.project.doctor_fish_back.entity.Doctor;
import com.project.doctor_fish_back.repository.DoctorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorMapper doctorMapper;

    public RespGetDoctorListDto getDoctors() {
        List<Doctor> doctors = doctorMapper.getAll();
        Long doctorCount = doctorMapper.getCountAll();

        return RespGetDoctorListDto.builder()
                .doctors(doctors)
                .doctorCount(doctorCount)
                .build();
    }
}