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

    // 관리자 페이지 연차 신청 페이지에 본인 연차 조회
    List<Leave> getLeavesByUserId(Long userId);
    Long getCountLeaveByUserId(Long userId);

    // 관리자 페이지 연차 관리 페이지에 전체 조회
    List<Leave> getLeaves();
    Long getCountLeaves();
}
