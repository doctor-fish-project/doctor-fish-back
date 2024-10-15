package com.project.doctor_fish_back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Doctor {
    private Long id;
    private Long userId;
    private Long departId;
    private String comment;
    private String record;

    private User user;
    private Depart depart;
}