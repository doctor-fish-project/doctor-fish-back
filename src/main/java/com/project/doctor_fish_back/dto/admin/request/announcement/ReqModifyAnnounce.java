package com.project.doctor_fish_back.dto.admin.request.announcement;

import com.project.doctor_fish_back.entity.Announcement;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReqModifyAnnounce {
    @NotBlank(message = "제목을 입력하세요.")
    private String title;
    @NotBlank(message = "내용을 입력하세요.")
    private String content;

    public Announcement toEntity(Long id) {
        return Announcement.builder()
                .id(id)
                .title(title)
                .content(content)
                .build();
    }
}
