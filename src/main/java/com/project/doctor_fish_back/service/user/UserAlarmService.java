package com.project.doctor_fish_back.service.user;

import com.project.doctor_fish_back.dto.user.response.alarm.RespAlarmDto;
import com.project.doctor_fish_back.entity.AlarmCheck;
import com.project.doctor_fish_back.entity.AlarmInsert;
import com.project.doctor_fish_back.repository.user.UserAlarmMapper;
import com.project.doctor_fish_back.repository.user.UserAnnouncementMapper;
import com.project.doctor_fish_back.repository.user.UserReservationMapper;
import com.project.doctor_fish_back.security.principal.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAlarmService {

    @Autowired
    private UserAlarmMapper alarmMapper;

    public List<RespAlarmDto> getAlarms() {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<AlarmInsert> alarmInserts = alarmMapper.alarmList(principalUser.getId());


        return alarmInserts.stream().map(AlarmInsert::toDto).collect(Collectors.toList());
    }

    public Boolean checkAlarms(List<Long> alarmsId) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        for(Long alarmId : alarmsId) {
            alarmMapper.save(AlarmCheck.builder()
                            .userId(principalUser.getId())
                            .alarmInsertId(alarmId)
                            .build());
        }

        return true;
    }

}
