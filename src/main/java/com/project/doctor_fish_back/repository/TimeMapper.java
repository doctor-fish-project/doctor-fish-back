package com.project.doctor_fish_back.repository;

import com.project.doctor_fish_back.dto.request.time.ReqReservedTimeDto;
import com.project.doctor_fish_back.entity.Time;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TimeMapper {
    List<Time> getTimes();
    List<Time> getReservedTimes(
            Map<String, Object> params);

}
