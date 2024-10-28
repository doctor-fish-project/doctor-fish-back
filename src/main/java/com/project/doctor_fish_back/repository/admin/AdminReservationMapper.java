package com.project.doctor_fish_back.repository.admin;

import com.project.doctor_fish_back.entity.Reservation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminReservationMapper {
    int acceptById(Long id);
    int cancelById(Long id);
    int deleteById(Long id);

    Reservation findById(Long id);

    // 관리자 페이지 전체 예약 조회
    List<Reservation> getAll(@Param("startIndex") Long startIndex, @Param("limit") Long limit);
    Long getCountAll();

    // 관리자 페이지 오늘 예약 조회
    List<Reservation> getToday(@Param("startIndex") Long startIndex, @Param("limit") Long limit);
    Long getCountToday();

    // 대쉬보드
    List<Reservation> getAllByLimit(Long limit);
    List<Reservation> getTodayByLimit(Long limit);
    
    // 관지자 페이지 대쉬보드 의사별 예약 수 조회
    List<Map<String, Object>> getMonthCountsByDoctors();

    // 관리자 페이지 의사별 예약 조회
    List<Reservation> getReservationsByDoctorId(Long doctorId);
    Long getCountReservationsByDoctorId(Long doctorId);
    
    // 관리자 페이지 예약 검색 조회
    List<Reservation> getReservationsBySearch(String searchText);
    Long getCountReservationsBySearch(String searchText);

}