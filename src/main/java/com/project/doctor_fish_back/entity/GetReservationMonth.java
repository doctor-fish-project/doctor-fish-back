package com.project.doctor_fish_back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetReservationMonth {
    private int monthId;
    private String reservationDate;
    private String doctorName;
    private Long count;
}
