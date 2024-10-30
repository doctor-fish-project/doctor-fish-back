package com.project.doctor_fish_back.repository.admin;

import com.project.doctor_fish_back.entity.AlarmInsert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminAlarmInsertMapper {
    int save(AlarmInsert alarmInsert);
}
