package com.project.doctor_fish_back.repository.user;

import com.project.doctor_fish_back.entity.Announcement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserAnnouncementMapper {
    Long getCountAll();

    Announcement findById(Long id);

    List<Announcement> getAll(@Param("startIndex") Long startIndex,
                              @Param("limit") Long limit);

    List<Announcement> getAllByLimit(Long limit);
}
