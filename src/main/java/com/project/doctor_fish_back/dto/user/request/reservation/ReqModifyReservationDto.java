package com.project.doctor_fish_back.dto.user.request.reservation;

import com.project.doctor_fish_back.entity.Reservation;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReqModifyReservationDto {
    private Long doctorId;
    private LocalDateTime reserveDate;

    public Reservation toEntity(Long id) {
        return Reservation.builder()
                .id(id)
                .doctorId(doctorId)
                .reservationDate(reserveDate)
                .build();
        }
}
