package com.project.doctor_fish_back.service.user;

import com.project.doctor_fish_back.aspect.annotation.NotFoundAop;
import com.project.doctor_fish_back.dto.user.response.announcement.RespGetAnnounceDto;
import com.project.doctor_fish_back.dto.user.response.announcement.RespGetAnnounceListDto;
import com.project.doctor_fish_back.dto.user.request.reservation.ReqPageAndLimitDto;
import com.project.doctor_fish_back.entity.Announcement;
import com.project.doctor_fish_back.exception.ExecutionException;
import com.project.doctor_fish_back.repository.user.UserAnnouncementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAnnouncementService {

    @Autowired
    private UserAnnouncementMapper announcementMapper;

    public RespGetAnnounceListDto getAllAnnouncements(ReqPageAndLimitDto dto) {
        try {
            Long startIndex = (dto.getPage() - 1) * dto.getLimit();
            List<Announcement> announcements = announcementMapper.getAll(startIndex, dto.getLimit());
            Long totalCount = announcementMapper.getCountAll();

            return RespGetAnnounceListDto.builder()
                    .announcements(announcements)
                    .announceCount(totalCount)
                    .build();
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }

    }

    public RespGetAnnounceListDto getDashBoardAnnouncements() {
        try {
            Long limit = 6L;
            List<Announcement> announcements = announcementMapper.getAllByLimit(limit);

            return RespGetAnnounceListDto.builder()
                    .announcements(announcements)
                    .build();
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }

    }

    @NotFoundAop
    public RespGetAnnounceDto getAnnouncement(Long announceId) {
        try {
            Announcement announcement = announcementMapper.findById(announceId);

            return RespGetAnnounceDto.builder()
                    .id(announcement.getId())
                    .userId(announcement.getUserId())
                    .title(announcement.getTitle())
                    .content(announcement.getContent())
                    .registerDate(announcement.getRegisterDate())
                    .updateDate(announcement.getUpdateDate())
                    .build();
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }

    }

}

