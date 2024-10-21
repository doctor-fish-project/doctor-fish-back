package com.project.doctor_fish_back.repository.admin;

import com.project.doctor_fish_back.entity.Announcement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminAnnouncementMapper {
    int save(Announcement announcement);
    int modify(Announcement announcement);
    int deleteById(Long id);

    Long getCountAll();

    Announcement findById(Long id);

    List<Announcement> getAll(@Param("startIndex") Long startIndex,
                              @Param("limit") Long limit);

    List<Announcement> getAllByLimit(Long limit);
}
