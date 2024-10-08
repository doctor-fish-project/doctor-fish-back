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
        try {
            doctorMapper.save(dto.toEntity());
        } catch (Exception e) {
            throw new RuntimeException("실행 도중 오류가 발생했습니다.");
        }
        return true;
    }
}