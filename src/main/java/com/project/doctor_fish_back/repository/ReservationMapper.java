package com.project.doctor_fish_back.repository;

import com.project.doctor_fish_back.entity.Reservation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationMapper {
    int register(Reservation reservation);
}