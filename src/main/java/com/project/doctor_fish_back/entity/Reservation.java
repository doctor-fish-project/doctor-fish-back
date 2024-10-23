package com.project.doctor_fish_back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Reservation {
    private Long id;
    private Long userId;
    private Long doctorId;
    private int status;
    private LocalDateTime reservationDate;
    private LocalDateTime registerDate;
    private int reviewStatus;

    private Doctor doctor;
    private User user;
}