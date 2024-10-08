package com.project.doctor_fish_back.dto.response.reservation;

import com.project.doctor_fish_back.entity.Reservation;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class RespGetReservationListDto {
    private List<Reservation> reservations;
    private Integer totalCount;
}
