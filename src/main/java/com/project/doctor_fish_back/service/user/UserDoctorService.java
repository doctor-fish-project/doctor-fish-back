package com.project.doctor_fish_back.service.user;

import com.project.doctor_fish_back.dto.user.response.doctor.RespGetDoctorListDto;
import com.project.doctor_fish_back.entity.Doctor;
import com.project.doctor_fish_back.repository.user.UserDoctorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDoctorService {

    @Autowired
    private UserDoctorMapper doctorMapper;

    public RespGetDoctorListDto getDoctors() {
        List<Doctor> doctors = doctorMapper.getAll();
        Long doctorCount = doctorMapper.getCountAll();

        return RespGetDoctorListDto.builder()
                .doctors(doctors)
                .doctorCount(doctorCount)
                .build();
    }
}