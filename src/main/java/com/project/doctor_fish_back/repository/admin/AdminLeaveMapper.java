package com.project.doctor_fish_back.repository.admin;

import com.project.doctor_fish_back.entity.Leave;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminLeaveMapper {
    int save(Leave leave);
    int modify(Leave leave);
    int acceptById(Long id);
    int cancelById(Long id);
    int deleteById(Long id);

    Leave findById(Long id);

    // 관리자 페이지 연차 신청 페이지에 본인 연차 조회
    List<Leave> leaveListByUserId(@Param("userId") Long userId, @Param("startIndex") Long startIndex, @Param("limit") Long limit);
    Long leavesCountByUserId(Long userId);

    // 관리자 페이지 연차 관리 페이지에 전체 조회
    List<Leave> leaveList(@Param("startIndex") Long startIndex, @Param("limit") Long limit);
    Long leavesCount();
}
