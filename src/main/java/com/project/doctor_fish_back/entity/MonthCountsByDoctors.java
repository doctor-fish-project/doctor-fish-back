package com.project.doctor_fish_back.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MonthCountsByDoctors {
    private Integer doctorId;
    private String name;
    private List<Integer> counts;
}
