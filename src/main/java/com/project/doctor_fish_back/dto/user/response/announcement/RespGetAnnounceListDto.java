package com.project.doctor_fish_back.dto.user.response.announcement;

import com.project.doctor_fish_back.entity.Announcement;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class RespGetAnnounceListDto {
    private List<Announcement> announcements;
    private Long announceCount;
}
