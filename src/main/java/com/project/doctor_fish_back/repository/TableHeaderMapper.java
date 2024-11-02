package com.project.doctor_fish_back.repository;

import com.project.doctor_fish_back.entity.TableHeader;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TableHeaderMapper {
    List<TableHeader> tableheaderList(String pathName);
}
