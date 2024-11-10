package com.project.doctor_fish_back.dto.user.request.user;

import com.project.doctor_fish_back.entity.User;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Pattern;

@Data
public class ReqModifyUserPasswordDto {
    private String email;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[~!@#$%^&*?])[A-Za-z\\d~!@#$%^&*?]{8,16}$", message = "8자이상 16자이하의 영대소문, 숫자, 특수문자(~!@#$%^&*?)")
    private String password;
    private String checkPassword;

    public User toEntity(BCryptPasswordEncoder passwordEncoder) {
        return User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();
    }
}
