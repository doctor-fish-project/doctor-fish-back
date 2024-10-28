package com.project.doctor_fish_back.dto.admin.response.reservation;

import com.project.doctor_fish_back.entity.Month;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class RespMonthReservationsCountByDoctorsDto {
    private List<Map<String, Object>> reservations;
    private List<Month> months;
}
