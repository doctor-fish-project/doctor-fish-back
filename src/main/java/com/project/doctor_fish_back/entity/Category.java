package com.project.doctor_fish_back.entity;

import com.project.doctor_fish_back.dto.category.RespCategoryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Category {
    Long id;
    String name;
    String icon;
    String link;

    public RespCategoryDto toDto() {
        return RespCategoryDto.builder()
                .categoryId(id)
                .name(name)
                .icon(icon)
                .link(link)
                .build();
    }
}
