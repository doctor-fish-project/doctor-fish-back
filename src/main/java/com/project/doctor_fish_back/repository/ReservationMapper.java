package com.project.doctor_fish_back.repository;

import com.project.doctor_fish_back.entity.Reservation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationMapper {
    int register(Reservation reservation);
    int acceptById(Long id);
    int cancelById(Long id);
    Reservation findById(Long id);
    int deleteById(Long id);

    List<Reservation> getToUser(Long userId);
    Long getCountToUser(Long userId);

    List<Reservation> getAllToUser(Long userId);
    Long getCountAllToUser(Long userId);

    List<Reservation> getAllToDoctor(Long doctorId);
    Long getCountAllToDoctor(Long doctorId);

    List<Reservation> getAllToInfo();
    Long getCountAllToInfo();

}