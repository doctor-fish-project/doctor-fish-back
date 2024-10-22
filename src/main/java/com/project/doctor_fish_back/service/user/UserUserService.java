package com.project.doctor_fish_back.service.user;

import com.project.doctor_fish_back.aspect.annotation.AuthorityAop;
import com.project.doctor_fish_back.aspect.annotation.NotFoundAop;
import com.project.doctor_fish_back.dto.user.response.auth.RespSigninDto;
import com.project.doctor_fish_back.dto.user.response.user.RespUserInfoDto;
import com.project.doctor_fish_back.dto.user.request.auth.ReqSigninDto;
import com.project.doctor_fish_back.dto.user.request.auth.ReqSignupDto;
import com.project.doctor_fish_back.dto.user.request.user.ReqModifyUserDto;
import com.project.doctor_fish_back.dto.user.request.user.ReqModifyUserEmailDto;
import com.project.doctor_fish_back.dto.user.request.user.ReqModifyUserPasswordDto;
import com.project.doctor_fish_back.entity.*;
import com.project.doctor_fish_back.exception.EmailValidException;
import com.project.doctor_fish_back.exception.SigninException;
import com.project.doctor_fish_back.exception.SignupException;
import com.project.doctor_fish_back.repository.user.RoleMapper;
import com.project.doctor_fish_back.repository.user.UserUserMapper;
import com.project.doctor_fish_back.repository.user.UserUserRolesMapper;
import com.project.doctor_fish_back.security.jwt.JwtProvider;
import com.project.doctor_fish_back.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserUserService {

    @Value("${user.profile.user.img.default}")
    private String userDefaultProfileImg;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserUserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserUserRolesMapper userRolesMapper;
    @Autowired
    private EmailService emailService;
    private ContentNegotiatingViewResolver viewResolver;

    @Transactional(rollbackFor = SignupException.class)
    public Boolean insertUserAndUserRoles(ReqSignupDto dto) throws SignupException {
        User user = null;
        try {
            user = dto.toEntity(passwordEncoder, userDefaultProfileImg);
            userMapper.save(user);

            Role role = roleMapper.findByPosition("ROLE_USER");

            if (role == null) {
                role = Role.builder().name("회원").position("ROLE_USER").build();
                roleMapper.save(role);
            }

            UserRoles userRoles = UserRoles.builder()
                    .userId(user.getId())
                    .roleId(role.getId())
                    .build();

            userRolesMapper.save(userRoles);

            user.setUserRoles(Set.of(userRoles));
        } catch (Exception e) {
            throw new SignupException(e.getMessage());
        }

        emailService.sendAuthMail(dto.getEmail());

        return true;
    }

    public RespSigninDto getGeneratedAccessToken(ReqSigninDto dto) throws SigninException {
        User user = checkUsernameAndPassword(dto.getEmail(), dto.getPassword());

        if(user.getEmailValid() != 1) {
            emailService.sendAuthMail(dto.getEmail());
            throw new EmailValidException("이메일 인증 후 로그인 가능합니다.");
        }

        return RespSigninDto.builder()
                .expireDate(jwtProvider.getExpireDate().toLocaleString())
                .accessToken(jwtProvider.generateAccessToken(user))
                .build();
    }

    private User checkUsernameAndPassword(String email, String password) throws SigninException {
        User user = userMapper.findByEmail(email);

        if(user == null) {
            throw new SigninException("사용자 정보를 다시 확인하세요.");
        }

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new SigninException("사용자 정보를 다시 확인하세요.");
        }

        return user;
    }

    public RespUserInfoDto getUserInfo(Long id) {
        User user = userMapper.findById(id);
        Set<String> roles = user.getUserRoles().stream().map(
                userRole -> userRole.getRole().getName()
        ).collect(Collectors.toSet());

        return RespUserInfoDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .img(user.getImg())
                .emailValid(user.getEmailValid())
                .registerDate(user.getRegisterDate())
                .updateDate(user.getUpdateDate())
                .roles(roles)
                .build();
    }

    @NotFoundAop
    @AuthorityAop
    public Boolean modifyUser(Long userId, ReqModifyUserDto dto) {
        if(dto.getImg() == null || dto.getImg().equals("")) {
            dto.setImg(userDefaultProfileImg);
        }

        userMapper.modify(dto.toEntity(userId));

        return true;
    }

    @NotFoundAop
    @AuthorityAop
    public Boolean modifyUserEmail(Long userId, ReqModifyUserEmailDto dto) {
        userMapper.modifyEmail(dto.toEntity(userId));
        userMapper.modifyEmailValidByEmail(dto.getEmail());
        emailService.sendAuthMail(dto.getEmail());

        return true;
    }

    @NotFoundAop
    @AuthorityAop
    public Boolean modifyUserPassword(Long userId, ReqModifyUserPasswordDto dto) {
        userMapper.modifyPassword(dto.toEntity(userId, passwordEncoder));
        return true;
    }

    @NotFoundAop
    @AuthorityAop
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteUser(Long userId) {
        try {
            userMapper.deleteById(userId);
            userRolesMapper.deleteByUserId(userId);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return true;
    }

}