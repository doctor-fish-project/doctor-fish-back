package com.project.doctor_fish_back.dto.admin.request.reservation;

import lombok.Data;

import java.util.Optional;

@Data
public class ReqPageAndLimitDto {
    private Long page;
    private Long limit;
}
