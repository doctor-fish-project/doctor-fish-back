package com.project.doctor_fish_back.repository.user;

import com.project.doctor_fish_back.entity.AlarmCheck;
import com.project.doctor_fish_back.entity.AlarmInsert;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface UserAlarmMapper {
    int save(AlarmCheck alarmCheck);

    List<AlarmInsert> alarmList(Long userId);

}
