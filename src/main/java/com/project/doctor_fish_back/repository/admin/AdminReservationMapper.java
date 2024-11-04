package com.project.doctor_fish_back.repository.admin;

import com.project.doctor_fish_back.dto.admin.response.reservation.RespYearDto;
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
    List<Reservation> reservationList(@Param("startIndex") Long startIndex,
                                      @Param("limit") Long limit,
                                      @Param("searchText") String searchText);
    Long reservationsCount(String searchText);

    // 관리자 페이지 의사별 전체 예약 조회
    List<Reservation> reservationListByDoctorId(@Param("doctorId") Long doctorId,
                                                @Param("startIndex") Long startIndex,
                                                @Param("limit") Long limit,
                                                @Param("searchText") String searchText);
    Long reservationsCountByDoctorId(Long doctorId, String searchText);

    // 관리자 페이지 오늘 예약 조회
    List<Reservation> todayReservationList(@Param("startIndex") Long startIndex,
                                           @Param("limit") Long limit,
                                           @Param("searchText") String searchText);
    Long todayReservationsCount(String searchText);

    // 관리자 페이지 의사별 오늘 예약 조회
    List<Reservation> todayReservationListByDoctorId(@Param("doctorId") Long doctorId,
                                                     @Param("startIndex") Long startIndex,
                                                     @Param("limit") Long limit,
                                                     @Param("searchText") String searchText);
    Long todayReservationsCountByDoctorId(Long doctorId, String searchText);

    // 관리자 페이지 대쉬보드
    List<Reservation> reservationListByLimit(Long limit);
    List<Reservation> todayReservationListByLimit(Long limit);

    // 관리자 페이지 대쉬보드 의사별 조회
    List<Reservation> reservationListByLimitAndDoctorId(@Param("doctorId") Long doctorId,
                                                         @Param("limit") Long limit);
    List<Reservation> todayReservationListByLimitAndDoctorId(@Param("doctorId") Long doctorId,
                                                             @Param("limit")Long limit);
    
    // 관리자 페이지 대쉬보드 의사별 전체 예약 수 조회A
    List<Map<String, Object>> monthReservationsCountByDoctorIds(Integer year);

    // 관리자 페이지 대쉬보드 의사별 예약 수 조회
    List<Map<String, Object>> monthReservationsCountByDoctorId(@Param("doctorId") Long doctorId, @Param("year") Integer year);

    // 관리자 페이지 대쉬보드 주간 예약 수 조회
    List<Map<String, Object>> weekReservationCount();

    // 관리자 페이지 대쉬보드 월간 예약 수 조회
    List<Map<String, Object>> monthReservationCount(Integer year);

    // 관리자 페이지 예약 연도 조회
    List<RespYearDto> yearList();

}