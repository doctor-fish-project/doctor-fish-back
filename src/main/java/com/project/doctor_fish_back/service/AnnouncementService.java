package com.project.doctor_fish_back.service;

import com.project.doctor_fish_back.aspect.annotation.AuthorityAop;
import com.project.doctor_fish_back.aspect.annotation.NotFoundAop;
import com.project.doctor_fish_back.dto.request.announcement.ReqModifyAnnounce;
import com.project.doctor_fish_back.dto.request.announcement.ReqWriteAnnounceDto;
import com.project.doctor_fish_back.dto.response.announcement.RespGetAnnounceDto;
import com.project.doctor_fish_back.dto.response.announcement.RespGetAnnounceListDto;
import com.project.doctor_fish_back.entity.Announcement;
import com.project.doctor_fish_back.entity.User;
import com.project.doctor_fish_back.exception.AuthorityException;
import com.project.doctor_fish_back.repository.AnnouncementMapper;
import com.project.doctor_fish_back.repository.UserMapper;
import com.project.doctor_fish_back.security.principal.PrincipalUser;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementMapper announcementMapper;

    @Autowired
    private UserMapper userMapper;

    public Boolean writeAnnounce(ReqWriteAnnounceDto dto) throws AuthorityException {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userMapper.findById(principalUser.getId());

        Set<String> roles = user.getUserRoles().stream().map(
                userRole -> userRole.getRole().getName()
        ).collect(Collectors.toSet());

        if (!roles.contains("ROLE_USER")) {
            announcementMapper.save(dto.toEntity(principalUser.getId()));
            return true;
        }

        if (roles.size() == 1) {
            throw new AuthorityException("권한이 없습니다.");
        }

        announcementMapper.save(dto.toEntity(principalUser.getId()));

        return true;
    }

    @NotFoundAop
    @AuthorityAop
    public boolean modifyAnnounce(Long announceId, ReqModifyAnnounce dto) {
        announcementMapper.modify(dto.toEntity(announceId));
        return true;

    }

    @NotFoundAop
    @AuthorityAop
    public Boolean deleteAnnounce(Long announceId) {
        announcementMapper.deleteById(announceId);
        return true;
    }

    public RespGetAnnounceListDto getAllAnnouncements() {
        List<Announcement> announcements = announcementMapper.getAll();
        Long announceCount = announcementMapper.getCountAll();

        return RespGetAnnounceListDto.builder()
                .announcements(announcements)
                .announceCount(announceCount)
                .build();
    }

    public RespGetAnnounceListDto getDashBoardAnnouncements() {
        Long limit = 6L;
        List<Announcement> announcements = announcementMapper.getAllByLimit(limit);

        return RespGetAnnounceListDto.builder()
                .announcements(announcements)
                .build();
    }

    @NotFoundAop
    public RespGetAnnounceDto getAnnouncement(Long announceId) {

        Announcement announcement = announcementMapper.findById(announceId);

        return RespGetAnnounceDto.builder()
                .id(announcement.getId())
                .userId(announcement.getUserId())
                .title(announcement.getTitle())
                .content(announcement.getContent())
                .registerDate(announcement.getRegisterDate())
                .updateDate(announcement.getUpdateDate())
                .build();
    }

}

