package com.project.doctor_fish_back.repository.admin;

import com.project.doctor_fish_back.entity.Depart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminDepartMapper {
    int save(Depart depart);

    Depart findById(Long departId);

    List<Depart> departList();
}
