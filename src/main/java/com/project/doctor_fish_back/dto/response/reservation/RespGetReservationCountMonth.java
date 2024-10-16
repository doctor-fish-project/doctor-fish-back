package com.project.doctor_fish_back.dto.response.reservation;

import com.project.doctor_fish_back.entity.GetReservationMonth;
import com.project.doctor_fish_back.entity.Month;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class RespGetReservationCountMonth {
    private List<Month> months;
    private List<GetReservationMonth> getReservationMonths;
}
