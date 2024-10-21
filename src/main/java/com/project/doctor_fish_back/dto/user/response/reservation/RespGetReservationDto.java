package com.project.doctor_fish_back.dto.user.response.reservation;

import com.project.doctor_fish_back.entity.Doctor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class RespGetReservationDto {
    private Long id;
    private Long userId;
    private Long doctorId;
    private int status;
    private LocalDateTime reservationDate;
    private LocalDateTime registerDate;

    private Doctor doctor;
}
