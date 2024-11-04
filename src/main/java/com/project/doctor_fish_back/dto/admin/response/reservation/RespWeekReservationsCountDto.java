package com.project.doctor_fish_back.dto.admin.response.reservation;

import com.project.doctor_fish_back.entity.Week;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RespWeekReservationsCountDto {
    private List<Integer> reservations;
    private List<Week> weeks;
}
