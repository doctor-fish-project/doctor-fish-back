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
    int getCountToUser(Long userId);

    List<Reservation> getAllToUser(Long userId);
    int getCountAllToUser(Long userId);

    List<Reservation> getAllToDoctor(Long doctorId);
    int getCountAllToDoctor(Long doctorId);

    List<Reservation> getAllToInfo();
    int getCountAllToInfo();

}