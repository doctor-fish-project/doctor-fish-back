package com.project.doctor_fish_back.service;

import com.project.doctor_fish_back.dto.request.doctor.ReqRegisterDoctorDto;
import com.project.doctor_fish_back.repository.DoctorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    private DoctorMapper doctorMapper;

    public Boolean registerDoctor(ReqRegisterDoctorDto dto) {
        doctorMapper.save(dto.toEntity());
        return true;
    }
}