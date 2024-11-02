package com.project.doctor_fish_back.repository.admin;

import com.project.doctor_fish_back.entity.Announcement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminAnnouncementMapper {
    // 관리자 페이지 공지사항 저장, 수정, 삭제
    int save(Announcement announcement);
    int modify(Announcement announcement);
    int deleteById(Long id);

    // 관리자 페이지 공지사항 단건 조회
    Announcement findById(Long id);

    // 관리자 페이지 대쉬보드 공지사항 조회
    List<Announcement> announcementListByLimit(Long limit);

    // 관리자 페이지 공지사항 전체 조회
    List<Announcement> announcementList(@Param("startIndex") Long startIndex, @Param("limit") Long limit, @Param("searchText") String searchText);
    Long announcementCount(String searchText);

}
