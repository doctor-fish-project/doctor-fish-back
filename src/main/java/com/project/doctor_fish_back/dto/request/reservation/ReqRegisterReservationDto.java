package com.project.doctor_fish_back.dto.request.reservation;

import com.project.doctor_fish_back.entity.Reservation;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReqRegisterReservationDto {
    private LocalDateTime reserveData;
    private Long doctorId;

    public Reservation toEntity(Long userId) {
        return Reservation.builder()
                .userId(userId)
                .doctorId(doctorId)
                .reservationDate(reserveData)
                .build();
    }
}