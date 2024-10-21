package com.project.doctor_fish_back.service;

import com.project.doctor_fish_back.entity.User;
import com.project.doctor_fish_back.exception.AccessTokenValidException;
import com.project.doctor_fish_back.repository.admin.AdminUserMapper;
import com.project.doctor_fish_back.security.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    private AdminUserMapper userMapper;

    @Autowired
    private JwtProvider jwtProvider;

    public Boolean isValidAccessToken(String bearerAccessToken) {
        try {
            System.out.println(bearerAccessToken);

            String accessToken = jwtProvider.removeBearer(bearerAccessToken);
            Claims claims = jwtProvider.getClaims(accessToken);
            Long userId = ((Integer) claims.get("userId")).longValue();
            User user = userMapper.findById(userId);

            if(user == null) {
                throw new RuntimeException();
            }
        } catch (RuntimeException e) {
            throw new AccessTokenValidException("AccessToken 유효성 검사 실패");
        }

        return true;
    }
}
