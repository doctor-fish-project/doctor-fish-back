package com.project.doctor_fish_back.repository.user;

import com.project.doctor_fish_back.entity.Announcement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserAnnouncementMapper {
    // 유저 페이지 공지사항 단건 조회
    Announcement findById(Long id);

    // 유저 페이지 공지사항 조회
    List<Announcement> announcementList(@Param("startIndex") Long startIndex,
                                        @Param("limit") Long limit);
    Long announcementCount();
}
