package com.project.doctor_fish_back.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.doctor_fish_back.security.principal.PrincipalUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private Long id;
    private String email;
    private String name;
    @JsonIgnore
    private String password;
    private String phoneNumber;
    private String img;
    private int emailValid;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
    private Set<UserRoles> userRoles;

    public PrincipalUser toPrincipal() {
        return PrincipalUser.builder()
                .id(id)
                .email(email)
                .password(password)
                .roles(userRoles)
                .build();
    }

}