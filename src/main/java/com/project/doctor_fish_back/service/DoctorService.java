package com.project.doctor_fish_back.service;

import com.project.doctor_fish_back.dto.request.doctor.ReqModifyDoctorDto;
import com.project.doctor_fish_back.dto.request.doctor.ReqModifyDoctorPasswordDto;
import com.project.doctor_fish_back.dto.request.doctor.ReqModifyDoctorUsernameDto;
import com.project.doctor_fish_back.dto.response.doctor.RespGetDoctorListDto;
import com.project.doctor_fish_back.entity.Doctor;
import com.project.doctor_fish_back.entity.User;
import com.project.doctor_fish_back.repository.DoctorMapper;
import com.project.doctor_fish_back.repository.UserMapper;
import com.project.doctor_fish_back.repository.UserRolesMapper;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DoctorService {

    @Value("${user.profile.doctor.img.default}")
    private String doctorDefaultProfileImg;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private DoctorMapper doctorMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRolesMapper userRolesMapper;

    public RespGetDoctorListDto getDoctors() {
        List<Doctor> doctors = doctorMapper.getAll();
        Long doctorCount = doctorMapper.getCountAll();

        return RespGetDoctorListDto.builder()
                .doctors(doctors)
                .doctorCount(doctorCount)
                .build();
    }

    public Boolean modifyDoctor(Long doctorId, ReqModifyDoctorDto dto) throws NotFoundException {
        Doctor doctor = doctorMapper.findById(doctorId);

        if(doctor == null) {
            throw new NotFoundException("해당 사용자를 찾을 수 없습니다.");
        }

        if(dto.getImg() == null || dto.getImg().equals("")) {
            dto.setImg(doctorDefaultProfileImg);
        }

        doctorMapper.modify(dto.toEntity(doctorId));

        User user = User.builder()
                .id(doctor.getUserId())
                .name(dto.getName())
                .img(dto.getImg())
                .build();
        userMapper.modify(user);

        return true;
    }

    public Boolean modifyDoctorUsername(Long doctorId, ReqModifyDoctorUsernameDto dto) throws NotFoundException {
        Doctor doctor = doctorMapper.findById(doctorId);

        if(doctor == null) {
            throw new NotFoundException("해당 사용자를 찾을 수 없습니다.");
        }

        User user = User.builder()
                .id(doctor.getUserId())
                .email(dto.getUsername())
                .build();

        userMapper.modifyEmail(user);

        return true;
    }

    public Boolean modifyDoctorPassword(Long doctorId, ReqModifyDoctorPasswordDto dto) throws NotFoundException {
        Doctor doctor = doctorMapper.findById(doctorId);

        if(doctor == null) {
            throw new NotFoundException("해당 사용자를 찾을 수 없습니다.");
        }

        User user = User.builder()
                .id(doctor.getUserId())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();

        userMapper.modifyPassword(user);

        return true;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteDoctor(Long doctorId) throws NotFoundException {
        try {
            Doctor doctor = doctorMapper.findById(doctorId);

            if(doctor == null) {
                throw new NotFoundException("해당 사용자를 찾을 수 없습니다.");
            }

            doctorMapper.deleteById(doctorId);
            userMapper.deleteById(doctor.getUserId());
            userRolesMapper.deleteByUserId(doctor.getUserId());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return true;
    }


}