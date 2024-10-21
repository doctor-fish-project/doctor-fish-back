package com.project.doctor_fish_back.dto.admin.request.user;

import com.project.doctor_fish_back.entity.User;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Pattern;

@Data
public class ReqModifyUserPasswordDto {
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[~!@#$%^&*?])[A-Za-z\\d~!@#$%^&*?]{8,16}$", message = "비밀번호는 8자이상 16자이하의 영대소문, 숫자, 특수문자(~!@#$%^&*?)를 포함하여합니다.")
    private String password;
    private String checkPassword;

    public User toEntity(Long userId, BCryptPasswordEncoder passwordEncoder) {
        return User.builder()
                .id(userId)
                .password(passwordEncoder.encode(password))
                .build();
    }
}
