package com.project.doctor_fish_back.repository.admin;

import com.project.doctor_fish_back.entity.Leave;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminLeaveMapper {
    int save(Leave leave);
    Leave findLeaveById(Long id);
    int modify(Leave leave);
    int acceptById(Long id);
    int cancelById(Long id);
    int deleteById(Long id);

    List<Leave> getAllToDoctor(Long userId);
    Long getCountAllToDoctor(Long userId);

    List<Leave> getAllToInfo();
    Long getCountAllToInfo();
}
