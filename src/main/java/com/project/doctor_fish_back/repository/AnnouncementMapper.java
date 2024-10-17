package com.project.doctor_fish_back.repository;

import com.project.doctor_fish_back.entity.Announcement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnnouncementMapper {
    int save(Announcement announcement);
    int modify(Announcement announcement);
    int deleteById(Long id);

    Long getCountAll();

    Announcement findById(Long id);

    List<Announcement> getAll();
    List<Announcement> getAllByLimit(Long limit);
}
