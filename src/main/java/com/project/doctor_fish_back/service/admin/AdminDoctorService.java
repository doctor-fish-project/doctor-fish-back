package com.project.doctor_fish_back.service.admin;

import com.project.doctor_fish_back.aspect.annotation.NotFoundAop;
import com.project.doctor_fish_back.dto.admin.request.doctor.ReqModifyDoctorDto;
import com.project.doctor_fish_back.dto.admin.response.doctor.RespGetDoctorListDto;
import com.project.doctor_fish_back.entity.Doctor;
import com.project.doctor_fish_back.entity.User;
import com.project.doctor_fish_back.exception.ExecutionException;
import com.project.doctor_fish_back.repository.admin.AdminDoctorMapper;
import com.project.doctor_fish_back.repository.admin.AdminUserMapper;
import com.project.doctor_fish_back.repository.admin.AdminUserRolesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminDoctorService {

    @Value("${user.profile.doctor.img.default}")
    private String doctorDefaultProfileImg;

    @Autowired
    private AdminDoctorMapper doctorMapper;
    @Autowired
    private AdminUserMapper userMapper;
    @Autowired
    private AdminUserRolesMapper userRolesMapper;

    public RespGetDoctorListDto getDoctors() {
        try {
            List<Doctor> doctors = doctorMapper.getAll();
            Long doctorCount = doctorMapper.getCountAll();

            return RespGetDoctorListDto.builder()
                    .doctors(doctors)
                    .doctorCount(doctorCount)
                    .build();
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }

    @NotFoundAop
    public Boolean modifyDoctor(Long doctorId, ReqModifyDoctorDto dto) {
        try {
            Doctor doctor = doctorMapper.findById(doctorId);

            if(dto.getImg() == null || dto.getImg().equals("")) {
                dto.setImg(doctorDefaultProfileImg);
            }

            doctorMapper.modify(dto.toEntity(doctorId));

            User user = User.builder()
                    .id(doctor.getUserId())
                    .name(dto.getName())
                    .phoneNumber(dto.getPhoneNumber())
                    .img(dto.getImg())
                    .build();
            userMapper.modify(user);
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }

        return true;
    }

    @NotFoundAop
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteDoctor(Long doctorId) {
        try {
            Doctor doctor = doctorMapper.findById(doctorId);

            doctorMapper.deleteById(doctorId);
            userMapper.deleteById(doctor.getUserId());
            userRolesMapper.deleteByUserId(doctor.getUserId());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return true;
    }


}