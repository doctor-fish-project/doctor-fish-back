package com.project.doctor_fish_back.service;

import com.project.doctor_fish_back.dto.changePassword.RespGetChangePasswordDto;
import com.project.doctor_fish_back.entity.ChangePassword;
import com.project.doctor_fish_back.repository.ChangePasswordMapper;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangePasswordService {

    @Autowired
    private ChangePasswordMapper changePasswordMapper;

    public RespGetChangePasswordDto getChangePassword(Long userId) throws NotFoundException {
        ChangePassword changePassword = changePasswordMapper.findByUserId(userId);
        if(changePassword == null) {
            throw new NotFoundException("해당 사용자를 찾을 수 없습니다.");
        }

        return RespGetChangePasswordDto.builder()
                .id(changePassword.getId())
                .email(changePassword.getEmail())
                .status(changePassword.getStatus())
                .build();
    }

}
