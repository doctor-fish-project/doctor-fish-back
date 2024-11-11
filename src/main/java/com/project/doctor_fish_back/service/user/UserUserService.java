package com.project.doctor_fish_back.service.user;

import com.project.doctor_fish_back.dto.user.response.auth.RespSigninDto;
import com.project.doctor_fish_back.dto.user.response.user.RespUserInfoDto;
import com.project.doctor_fish_back.dto.user.request.auth.ReqSigninDto;
import com.project.doctor_fish_back.dto.user.request.auth.ReqSignupDto;
import com.project.doctor_fish_back.dto.user.request.user.ReqModifyUserDto;
import com.project.doctor_fish_back.dto.user.request.user.ReqModifyUserEmailDto;
import com.project.doctor_fish_back.dto.user.request.user.ReqModifyUserPasswordDto;
import com.project.doctor_fish_back.entity.*;
import com.project.doctor_fish_back.exception.EmailValidException;
import com.project.doctor_fish_back.exception.ExecutionException;
import com.project.doctor_fish_back.exception.SigninException;
import com.project.doctor_fish_back.exception.SignupException;
import com.project.doctor_fish_back.repository.ChangePasswordMapper;
import com.project.doctor_fish_back.repository.RoleMapper;
import com.project.doctor_fish_back.repository.UserRolesMapper;
import com.project.doctor_fish_back.repository.user.UserUserMapper;
import com.project.doctor_fish_back.security.jwt.JwtProvider;
import com.project.doctor_fish_back.security.principal.PrincipalUser;
import com.project.doctor_fish_back.service.EmailService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private UserRolesMapper userRolesMapper;
    @Autowired
    private EmailService emailService;
    @Autowired
    private ChangePasswordMapper changePasswordMapper;
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

            emailService.sendAuthMail(dto.getEmail());
        } catch (Exception e) {
            throw new SignupException(e.getMessage());
        }

        return true;
    }

    public RespSigninDto getGeneratedAccessToken(ReqSigninDto dto) throws SigninException {
        try {
            User user = checkUsernameAndPassword(dto.getEmail(), dto.getPassword());

            if(user.getEmailValid() != 1) {
                emailService.sendAuthMail(dto.getEmail());
                throw new EmailValidException("이메일 인증 후 로그인 가능합니다.");
            }

            return RespSigninDto.builder()
                    .expireDate(jwtProvider.getExpireDate().toLocaleString())
                    .accessToken(jwtProvider.generateAccessToken(user))
                    .build();
        } catch (SigninException e) {
            throw new SigninException(e.getMessage());
        } catch (EmailValidException e) {
            throw new EmailValidException(e.getMessage());
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }

    private User checkUsernameAndPassword(String email, String password) throws SigninException {
        try {
            User user = userMapper.findByEmail(email);

            if(user == null) {
                throw new SigninException("사용자 정보를 다시 확인하세요.");
            }

            if(!passwordEncoder.matches(password, user.getPassword())) {
                throw new SigninException("사용자 정보를 다시 확인하세요.");
            }

            return user;
        } catch (SigninException e) {
            throw new SigninException(e.getMessage());
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }

    public RespUserInfoDto getUserInfo(Long id) {
        try {
            User user = userMapper.findById(id);
            Set<Role> roles = user.getUserRoles().stream().map(
                    userRole -> userRole.getRole()).collect(Collectors.toSet());

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
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }

    public Boolean modifyUser(ReqModifyUserDto dto) {
        try {
            PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if(dto.getImg() == null || dto.getImg().equals("")) {
                dto.setImg(userDefaultProfileImg);
            }

            userMapper.modify(dto.toEntity(principalUser.getId()));
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
        return true;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean modifyUserEmail(ReqModifyUserEmailDto dto) {
        try {
            PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            userMapper.modifyEmail(dto.toEntity(principalUser.getId()));
            userMapper.modifyEmailValidByEmail(dto.getEmail());
            emailService.sendAuthMail(dto.getEmail());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return true;
    }

    public Boolean modifyUserPassword(ReqModifyUserPasswordDto dto) {
        try {
            userMapper.modifyPassword(dto.toEntity(passwordEncoder));
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
        return true;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteUser() {
        try {
            PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            userMapper.deleteById(principalUser.getId());
            userRolesMapper.deleteByUserId(principalUser.getId());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return true;
    }

    public Long getUserByEmail(String email) throws NotFoundException {
        User user = userMapper.findByEmail(email);

        System.out.println(email);
        System.out.println(user);

        if (user == null) {
            throw new NotFoundException("사용자를 찾을 수 없습니다.");
        }

        ChangePassword changePassword = changePasswordMapper.findByEmail(user.getEmail());
        if(changePassword == null) {
            changePasswordMapper.save(ChangePassword.builder()
                            .email(user.getEmail())
                            .build());
            emailService.sendChangePasswordMail(user.getEmail());
            return user.getId();
        }

        changePasswordMapper.modifyStatusByEmail(user.getEmail());
        emailService.sendChangePasswordMail(user.getEmail());
        return user.getId();
    }

}