package com.project.doctor_fish_back.service;

import com.project.doctor_fish_back.dto.time.ReqReservedTimeDto;
import com.project.doctor_fish_back.entity.Doctor;
import com.project.doctor_fish_back.entity.Time;
import com.project.doctor_fish_back.exception.ExecutionException;
import com.project.doctor_fish_back.repository.TimeMapper;
import com.project.doctor_fish_back.repository.admin.AdminDoctorMapper;
import com.project.doctor_fish_back.security.principal.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TimeService {

    @Autowired
    private AdminDoctorMapper adminDoctorMapper;

    @Autowired
    private TimeMapper timeMapper;  // Assuming TimeMapper is a DAO (Data Access Object) interface for the Time entity

    public List<Time> getTimeList(String leaveDate) {
        try {
            PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Doctor doctor = adminDoctorMapper.findByUserId(principalUser.getId());
            if(doctor == null) {
                return timeMapper.timeList();
            }
            return timeMapper.availableTimeList(doctor.getId(), leaveDate);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }

    public List<Time> getReservedTimeList(ReqReservedTimeDto dto) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("doctorId", dto.getDoctorId());
            params.put("reservationDate", dto.getReservationDate().substring(0, dto.getReservationDate().indexOf("T")));
            return timeMapper.reservedTimeList(params);
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }
}
