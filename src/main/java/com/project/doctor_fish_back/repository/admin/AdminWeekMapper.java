package com.project.doctor_fish_back.repository.admin;

import com.project.doctor_fish_back.entity.Week;
import org.apache.ibatis.annotations.Mapper;

import java.rmi.MarshalledObject;
import java.util.List;
import java.util.Map;

@Mapper
public interface AdminWeekMapper {

    List<Week> weekList();
}
