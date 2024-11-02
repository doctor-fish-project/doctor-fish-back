package com.project.doctor_fish_back.service.admin;

import com.project.doctor_fish_back.dto.admin.request.announcement.ReqModifyAnnounce;
import com.project.doctor_fish_back.dto.admin.request.announcement.ReqWriteAnnounceDto;
import com.project.doctor_fish_back.dto.admin.request.reservation.ReqPageAndLimitDto;
import com.project.doctor_fish_back.dto.admin.response.announcement.RespGetAnnounceDto;
import com.project.doctor_fish_back.dto.admin.response.announcement.RespGetAnnounceListDto;
import com.project.doctor_fish_back.entity.*;
import com.project.doctor_fish_back.exception.AuthorityException;
import com.project.doctor_fish_back.exception.ExecutionException;
import com.project.doctor_fish_back.repository.admin.AdminAlarmInsertMapper;
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

    @Autowired
    private AdminAlarmInsertMapper alarmInsertMapper;
    
    // 관리자 페이지 공지사항 작성
    public Boolean writeAnnounce(ReqWriteAnnounceDto dto) throws AuthorityException {
        Announcement announcement = null;
        try {
            PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            User user = userMapper.findById(principalUser.getId());

            Set<String> roles = user.getUserRoles().stream().map(
                    userRole -> userRole.getRole().getPosition()
            ).collect(Collectors.toSet());

            if (!roles.contains("ROLE_USER")) {
                announcement = dto.toEntity(principalUser.getId());
                announcementMapper.save(announcement);

                alarmInsertMapper.save(AlarmInsert.builder()
                                .typeId(2L)
                                .alarmId(announcement.getId())
                                .messageId(2L)
                                .build());
                return true;
            }
            if (roles.size() == 1) {
                throw new AuthorityException("권한이 없습니다.");
            }

        } catch (AuthorityException e) {
            throw new AuthorityException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExecutionException("실행 도중 오류 발생");
        }

        return true;
    }
    
    // 관리자 페이지 공지사항 수정
    public boolean modifyAnnounce(Long announceId, ReqModifyAnnounce dto) {
        try {
            announcementMapper.modify(dto.toEntity(announceId));
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
        return true;

    }
    
    // 관리자 페이지 공지사항 삭제
    public Boolean deleteAnnounce(Long announceId) {
        try {
            announcementMapper.deleteById(announceId);
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
        return true;
    }

    public RespGetAnnounceListDto getDashBoardAnnouncements() {
        try {
            Long limit = 6L;
            List<Announcement> announcements = announcementMapper.announcementListByLimit(limit);

            return RespGetAnnounceListDto.builder()
                    .announcements(announcements)
                    .build();
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }
    
    // 관리자 페이지 공지사항 전체 조회
    public RespGetAnnounceListDto getAllAnnouncements(ReqPageAndLimitDto dto, String searchText) {
        try {
            Long startIndex = (dto.getPage() - 1) * dto.getLimit();
            List<Announcement> announcements = announcementMapper.announcementList(startIndex, dto.getLimit(), searchText);
            Long totalCount = announcementMapper.announcementCount(searchText);

            return RespGetAnnounceListDto.builder()
                    .announcements(announcements)
                    .announceCount(totalCount)
                    .build();
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }

    }
    
    // 관리자 페이지 공지사항 단건 조회
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

