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
    List<Reservation> getReservations(@Param("startIndex") Long startIndex, @Param("limit") Long limit, @Param("searchText") String searchText);
    Long getCountReservations(String searchText);

    // 관리자 페이지 의사별 전체 예약 조회
    List<Reservation> getReservationsByDoctorId(@Param("doctorId") Long doctorId, @Param("startIndex") Long startIndex, @Param("limit") Long limit, @Param("searchText") String searchText);
    Long getCountReservationsByDoctorId(Long doctorId, String searchText);

    // 관리자 페이지 오늘 예약 조회
    List<Reservation> getTodayReservations(@Param("startIndex") Long startIndex, @Param("limit") Long limit, @Param("searchText") String searchText);
    Long getCountTodayReservations(String searchText);

    // 관리자 페이지 의사별 오늘 예약 조회
    List<Reservation> getTodayReservationsByDoctorId(@Param("doctorId") Long doctorId, @Param("startIndex") Long startIndex, @Param("limit") Long limit, @Param("searchText") String searchText);
    Long getCountTodayReservationsByDoctorId(Long doctorId, String searchText);

    // 관리자 페이지 대쉬보드
    List<Reservation> getAllByLimit(Long limit);
    List<Reservation> getTodayByLimit(Long limit);

    // 관리자 페이지 대쉬보드 의사별 조회
    List<Reservation> getDashBoardReservationsByDoctorId(@Param("doctorId") Long doctorId, @Param("limit") Long limit);
    List<Reservation> getDashBoardTodayReservationsByDoctorId(@Param("doctorId") Long doctorId, @Param("limit")Long limit);
    
    // 관리자 페이지 대쉬보드 의사별 전체 예약 수 조회
    List<Map<String, Object>> getMonthCountsByDoctors();

    // 관리자 페이지 대쉬보드 의사별 예약 수 조회
    List<Map<String, Object>> getMonthCountsByDoctorId(Long doctorId);

}