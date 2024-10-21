package com.project.doctor_fish_back.dto.user.request.auth;

import com.project.doctor_fish_back.entity.User;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class ReqSignupDto {
    @NotBlank(message = "이메일은 공백일 수 없습니다.")
    @Email(message = "이메일 형식이어야 합니다.")
    private String email;
    @NotBlank(message = "이름은 공백일 수 없습니다.")
    private String name;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[~!@#$%^&*?])[A-Za-z\\d~!@#$%^&*?]{8,16}$", message = "8자이상 16자이하의 영대소문, 숫자, 특수문자(~!@#$%^&*?)")
    private String password;
    private String checkPassword;
    @NotBlank(message = "전화번호는 공백일 수 없습니다.")
    private String phoneNumber;

    public User toEntity(BCryptPasswordEncoder passwordEncoder, String defaultProfileImg) {
        return User.builder()
                .email(email)
                .name(name)
                .password(passwordEncoder.encode(password))
                .phoneNumber(phoneNumber)
                .img(defaultProfileImg)
                .build();
    }
}
