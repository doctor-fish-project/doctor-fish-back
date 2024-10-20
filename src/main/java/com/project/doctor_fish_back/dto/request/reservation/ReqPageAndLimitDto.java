package com.project.doctor_fish_back.dto.request.reservation;

import lombok.Data;

import java.util.Optional;

@Data
public class ReqPageAndLimitDto {
    private Long page = 1L;
    private Long limit;
}
