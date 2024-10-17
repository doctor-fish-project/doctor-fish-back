package com.project.doctor_fish_back.dto.request.reservation;

import lombok.Data;

@Data
public class ReqPageAndLimitDto {
    private Long page;
    private Long limit;
}
