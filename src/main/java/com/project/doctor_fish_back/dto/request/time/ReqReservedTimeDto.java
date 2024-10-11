package com.project.doctor_fish_back.dto.request.time;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReqReservedTimeDto {
    private Long doctorId;
    private String reservationDate;
}
