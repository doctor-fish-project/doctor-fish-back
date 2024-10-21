package com.project.doctor_fish_back.dto.user.request.reservation;

import lombok.Data;

@Data
public class ReqPageAndLimitDto {
    private Long page = 1L;
    private Long limit;
}
