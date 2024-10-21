package com.project.doctor_fish_back.repository.admin;

import com.project.doctor_fish_back.entity.Month;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMonthMapper {
    List<Month> getAll();
}
