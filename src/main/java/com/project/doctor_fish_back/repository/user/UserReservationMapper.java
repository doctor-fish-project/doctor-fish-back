package com.project.doctor_fish_back.repository.user;

import com.project.doctor_fish_back.entity.Reservation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserReservationMapper {
    int register(Reservation reservation);
    int cancelById(Long id);
    Reservation findById(Long id);
    int deleteById(Long id);

    List<Reservation> getAll(@Param("startIndex") Long startIndex,
                             @Param("limit") Long limit);
    Long getCountAll();

    List<Reservation> getToday(@Param("startIndex") Long startIndex,
                               @Param("limit") Long limit);
    Long getCountToday();

    List<Reservation> getAllByLimit(Long limit);
    List<Reservation> getTodayByLimit(Long limit);

    List<Reservation> getToUser(Long userId);
    Long getCountToUser(Long userId);

    List<Reservation> getAllToUser(Long userId);
    Long getCountAllToUser(Long userId);


    int modify(Reservation reservation);

}