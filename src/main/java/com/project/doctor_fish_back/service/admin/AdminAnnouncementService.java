package com.project.doctor_fish_back.service.admin;

import com.project.doctor_fish_back.aspect.annotation.AuthorityAop;
import com.project.doctor_fish_back.aspect.annotation.NotFoundAop;
import com.project.doctor_fish_back.dto.admin.request.announcement.ReqModifyAnnounce;
import com.project.doctor_fish_back.dto.admin.request.announcement.ReqWriteAnnounceDto;
import com.project.doctor_fish_back.dto.admin.request.reservation.ReqPageAndLimitDto;
import com.project.doctor_fish_back.dto.admin.response.announcement.RespGetAnnounceDto;
import com.project.doctor_fish_back.dto.admin.response.announcement.RespGetAnnounceListDto;
import com.project.doctor_fish_back.dto.search.ReqSearchDto;
import com.project.doctor_fish_back.entity.Announcement;
import com.project.doctor_fish_back.entity.User;
import com.project.doctor_fish_back.exception.AuthorityException;
import com.project.doctor_fish_back.exception.ExecutionException;
import com.project.doctor_fish_back.repository.admin.AdminAnnouncementMapper;
import com.project.doctor_fish_back.repository.admin.AdminUserMapper;
import com.project.doctor_fish_back.security.principal.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdminAnnouncementService {

    @Autowired
    private AdminAnnouncementMapper announcementMapper;

    @Autowired
    private AdminUserMapper userMapper;

    public Boolean writeAnnounce(ReqWriteAnnounceDto dto) throws AuthorityException {
        try {
            PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            User user = userMapper.findById(principalUser.getId());

            Set<String> roles = user.getUserRoles().stream().map(
                    userRole -> userRole.getRole().getPosition()
            ).collect(Collectors.toSet());

            if (!roles.contains("ROLE_USER")) {
                announcementMapper.save(dto.toEntity(principalUser.getId()));
                return true;
            }

            if (roles.size() == 1) {
                throw new AuthorityException("권한이 없습니다.");
            }

            announcementMapper.save(dto.toEntity(principalUser.getId()));
        } catch (AuthorityException e) {
            throw new AuthorityException(e.getMessage());
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }

        return true;
    }

    @NotFoundAop
    @AuthorityAop
    public boolean modifyAnnounce(Long announceId, ReqModifyAnnounce dto) {
        try {
            announcementMapper.modify(dto.toEntity(announceId));
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
        return true;

    }

    @NotFoundAop
    @AuthorityAop
    public Boolean deleteAnnounce(Long announceId) {
        try {
            announcementMapper.deleteById(announceId);
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
        return true;
    }

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

    public RespGetAnnounceListDto searchAnnouncement(ReqSearchDto dto) {
        try {
            List<Announcement> announcements = announcementMapper.getBySearch(dto.getSearchText());
            Long announceCount = announcementMapper.getCountBySearch(dto.getSearchText());

            return RespGetAnnounceListDto.builder()
                    .announcements(announcements)
                    .announceCount(announceCount)
                    .build();
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }

}

