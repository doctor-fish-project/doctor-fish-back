package com.project.doctor_fish_back.dto.user.request.reservation;

import com.project.doctor_fish_back.entity.Reservation;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReqRegisterReservationDto {
    private LocalDateTime reserveDate;
    private Long doctorId;

    public Reservation toEntity(Long userId) {
        return Reservation.builder()
                .userId(userId)
                .doctorId(doctorId)
                .reservationDate(reserveDate)
                .build();
    }
}