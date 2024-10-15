package com.project.doctor_fish_back.repository;

import com.project.doctor_fish_back.entity.Announcement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnnouncementMapper {
    int save(Announcement announcement);
    int modify(Announcement announcement);
    Announcement findById(Long id);
    int deleteById(Long id);
    List<Announcement> getAll();
    Long getCountAll();
}
