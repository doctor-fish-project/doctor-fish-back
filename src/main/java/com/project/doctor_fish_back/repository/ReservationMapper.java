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

    List<Reservation> getAllToUser(Long userId);
    int getCountAllToUser(Long userId);

    List<Reservation> getAllToAdmin();
    int getCountAllToAdmin();

}