package com.project.doctor_fish_back.dto.admin.request.announcement;

import com.project.doctor_fish_back.entity.Announcement;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReqWriteAnnounceDto {
    @NotBlank(message = "제목을 입력하세요.")
    private String title;
    @NotBlank(message = "내용을 입력하세요.")
    private String content;

    public Announcement toEntity(Long userId) {
        return Announcement.builder()
                .userId(userId)
                .title(title)
                .content(content)
                .build();
    }
}
