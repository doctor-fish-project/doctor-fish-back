package com.project.doctor_fish_back.repository.admin;

import com.project.doctor_fish_back.entity.GetReservationMonthDoctors;
import com.project.doctor_fish_back.entity.Reservation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminReservationMapper {
    int acceptById(Long id);
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

    List<GetReservationMonthDoctors> getDoctors(String year);
    List<Integer> getCounts(Long doctorId);

    List<Reservation> getAllToDoctor(Long doctorId);
    Long getCountAllToDoctor(Long doctorId);

    List<Reservation> getAllToInfo();
    Long getCountAllToInfo();

}