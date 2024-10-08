package com.project.doctor_fish_back.service;

import com.project.doctor_fish_back.dto.request.reservation.ReqRegisterReservationDto;
import com.project.doctor_fish_back.entity.Reservation;
import com.project.doctor_fish_back.repository.ReservationMapper;
import com.project.doctor_fish_back.security.principal.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    private ReservationMapper reservationMapper;

    public Boolean registerReservation(ReqRegisterReservationDto dto) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Reservation reservation = dto.toEntity(principalUser.getId());
        reservationMapper.register(reservation);

        return true;
    }

}