package com.project.doctor_fish_back.repository;

import com.project.doctor_fish_back.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> categoryList(Long roleId);
}
