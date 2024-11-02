package com.project.doctor_fish_back.repository;

import com.project.doctor_fish_back.entity.Time;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TimeMapper {
    List<Time> timeList();

    List<Time> reservedTimeList(Map<String, Object> params);

    List<Time> availableTimeList(@Param("doctorId") Long doctorId,
                                 @Param("leaveDate") String leaveDate);
}
