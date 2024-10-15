package com.project.doctor_fish_back.repository;

import com.project.doctor_fish_back.entity.Depart;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartMapper {
    int save(Depart depart);
    Depart findByName(String name);
}
