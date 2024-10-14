package com.project.doctor_fish_back.service;

import com.project.doctor_fish_back.dto.request.doctor.ReqRegisterDoctorDto;
import com.project.doctor_fish_back.dto.response.doctor.RespGetDoctorListDto;
import com.project.doctor_fish_back.entity.Depart;
import com.project.doctor_fish_back.entity.Doctor;
import com.project.doctor_fish_back.entity.User;
import com.project.doctor_fish_back.repository.DepartMapper;
import com.project.doctor_fish_back.repository.DoctorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorMapper doctorMapper;

    @Autowired
    private DepartMapper departMapper;

    public Boolean insertDoctorAndDepart(ReqRegisterDoctorDto dto) {
        Depart depart = departMapper.findByName(dto.getDepartName());

        if(depart == null) {
            departMapper.save(Depart.builder().name(dto.getDepartName()).build());
            depart = departMapper.findByName(dto.getDepartName());
        }

        doctorMapper.save(dto.toEntity(depart.getId()));

        return true;
    }

    public RespGetDoctorListDto getDoctors() {
        List<Doctor> doctors = doctorMapper.getAll();
        Long doctorCount = doctorMapper.getCountAll();

        return RespGetDoctorListDto.builder()
                .doctors(doctors)
                .doctorCount(doctorCount)
                .build();
    }
}