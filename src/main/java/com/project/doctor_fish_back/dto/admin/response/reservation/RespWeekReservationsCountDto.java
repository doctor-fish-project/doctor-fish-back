package com.project.doctor_fish_back.dto.admin.response.reservation;

import com.project.doctor_fish_back.entity.Week;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class RespWeekReservationsCountDto {
    private List<Map<String, Object>> reservations;
    private List<Week> weeks;
}
