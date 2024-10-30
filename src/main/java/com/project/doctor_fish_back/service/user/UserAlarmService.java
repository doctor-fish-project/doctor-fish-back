package com.project.doctor_fish_back.service.user;

import com.project.doctor_fish_back.dto.user.response.alarm.RespAlarmDto;
import com.project.doctor_fish_back.repository.user.UserAlarmMapper;
import com.project.doctor_fish_back.repository.user.UserAnnouncementMapper;
import com.project.doctor_fish_back.repository.user.UserReservationMapper;
import com.project.doctor_fish_back.security.principal.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAlarmService {

    @Autowired
    private UserAlarmMapper alarmMapper;

    @Autowired
    private UserAnnouncementMapper announcementMapper;

    @Autowired
    private UserReservationMapper reservationMapper;




}
