package com.project.doctor_fish_back.service;

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

    public Boolean writeAnnounce(ReqWriteAnnounceDto dto) throws NotFoundException, AuthorityException {
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

    public boolean modifyAnnounce(Long announceId, ReqModifyAnnounce dto) throws AuthorityException, NotFoundException {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Announcement announcement = announcementMapper.findById(announceId);

        if(announcement == null) {
            throw new NotFoundException("해당 공지사항 찾을 수 없습니다.");
        }

        if(announcement.getUserId() != principalUser.getId()) {
            throw new AuthorityException("권한이 없습니다.");
        }

        announcementMapper.modify(dto.toEntity(announceId));

        return true;

    }

    public Boolean deleteAnnounce(Long announceId) throws NotFoundException, AuthorityException {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Announcement announcement = announcementMapper.findById(announceId);

        if(announcement == null) {
            throw new NotFoundException("해당 공지사항 찾을 수 없습니다.");
        }

        if(announcement.getUserId() != principalUser.getId()) {
            throw new AuthorityException("권한이 없습니다.");
        }

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

    public RespGetAnnounceDto getAnnouncement(Long announceId) throws NotFoundException {
        Announcement announcement = announcementMapper.findById(announceId);

        if(announcement == null) {
            throw new NotFoundException("해당 공지사항 찾을 수 없습니다.");
        }

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

