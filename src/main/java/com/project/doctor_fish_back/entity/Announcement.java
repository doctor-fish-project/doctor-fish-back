package com.project.doctor_fish_back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Announcement {
    private Long id;
    private Long userId;
    private String title;
    private String content;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;

    private String userName;
    private String userImg;
}
