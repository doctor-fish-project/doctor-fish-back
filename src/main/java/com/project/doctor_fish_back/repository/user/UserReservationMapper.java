package com.project.doctor_fish_back.repository.user;

import com.project.doctor_fish_back.dto.admin.response.reservation.RespYearDto;
import com.project.doctor_fish_back.entity.Reservation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserReservationMapper {
    // 유저 페이지 예약 등록, 수정, 취소, 삭제
    int register(Reservation reservation);
    int cancelById(Long id);
    int deleteById(Long id);
    int modify(Reservation reservation);
    int modifyReviewStatusById(Long id);

    // 유저 페이지 예약 단건 조회
    Reservation findById(Long id);

    // 유저 페이지 오늘 예약 조회
    List<Reservation> todayReservationList(@Param("userId") Long userId);

    // 유저 페이지 예약 전체 조회
    List<Reservation> reservationList(@Param("userId") Long userId,
                                           @Param("startIndex") Long startIndex,
                                           @Param("limit") Long limit);
    Long reservationCount(Long userId);

    // 유저 페이지 리뷰 작성가능한 예약 전체 조회
    List<Reservation> reservationListForReview(Long userId);
    Long reservationCountForReview(Long userId);
}