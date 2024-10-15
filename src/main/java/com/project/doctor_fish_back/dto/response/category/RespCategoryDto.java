package com.project.doctor_fish_back.dto.response.category;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespCategoryDto {
    Long categoryId;
    String name;
    String icon;
    String link;
}
