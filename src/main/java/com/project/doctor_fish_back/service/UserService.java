package com.project.doctor_fish_back.service;

import com.project.doctor_fish_back.dto.request.auth.ReqSigninDto;
import com.project.doctor_fish_back.dto.request.auth.ReqSignupDto;
import com.project.doctor_fish_back.dto.request.user.ReqModifyUserDto;
import com.project.doctor_fish_back.dto.request.user.ReqModifyUserEmailDto;
import com.project.doctor_fish_back.dto.request.user.ReqModifyUserPasswordDto;
import com.project.doctor_fish_back.dto.response.auth.RespSigninDto;
import com.project.doctor_fish_back.dto.response.user.RespUserInfoDto;
import com.project.doctor_fish_back.entity.Role;
import com.project.doctor_fish_back.entity.User;
import com.project.doctor_fish_back.entity.UserRoles;
import com.project.doctor_fish_back.exception.AuthorityException;
import com.project.doctor_fish_back.exception.EmailValidException;
import com.project.doctor_fish_back.exception.SigninException;
import com.project.doctor_fish_back.exception.SignupException;
import com.project.doctor_fish_back.repository.RoleMapper;
import com.project.doctor_fish_back.repository.UserMapper;
import com.project.doctor_fish_back.repository.UserRolesMapper;
import com.project.doctor_fish_back.security.jwt.JwtProvider;
import com.project.doctor_fish_back.security.principal.PrincipalUser;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Value("${user.profile.img.default}")
    private String defaultProfileImg;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRolesMapper userRolesMapper;
    @Autowired
    private EmailService emailService;

    public Boolean isDuplicateEmail(String email) {
        try {
            return Optional.ofNullable(userMapper.findByEmail(email)).isPresent();
        } catch (Exception e) {
            throw new RuntimeException("실행 도중 오류가 발생했습니다.");
        }
    }

    @Transactional(rollbackFor = SignupException.class)
    public Boolean insertUserAndUserRoles(ReqSignupDto dto) throws SignupException {
        User user = null;
        try {
            user = dto.toEntity(passwordEncoder, defaultProfileImg);
            userMapper.save(user);

            Role role = roleMapper.findByName("ROLE_USER");

            if (role == null) {
                role = Role.builder().name("ROLE_USER").build();
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
                .roles(roles)
                .build();
    }

    public Boolean modifyUser(Long userId, ReqModifyUserDto dto) throws NotFoundException, AuthorityException {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userMapper.findById(userId);

        if(user == null) {
            throw new NotFoundException("해당 사용자를 찾을 수 없습니다.");
        }

        if(user.getId() != principalUser.getId()) {
            throw new AuthorityException("권한이 없습니다.");
        }

        if(dto.getImg() == null || dto.getImg().equals("")) {
            dto.setImg(defaultProfileImg);
        }

        userMapper.modify(dto.toEntity(userId));

        return true;
    }

    public Boolean modifyUserEmail(Long userId, ReqModifyUserEmailDto dto) throws NotFoundException, AuthorityException {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userMapper.findById(userId);

        if(user == null) {
            throw new NotFoundException("해당 사용자를 찾을 수 없습니다.");
        }

        if(user.getId() != principalUser.getId()) {
            throw new AuthorityException("권한이 없습니다.");
        }

        userMapper.modifyEmail(dto.toEntity(userId));
        userMapper.modifyEmailValidByEmail(dto.getEmail());
        emailService.sendAuthMail(dto.getEmail());

        return true;
    }

    public Boolean modifyUserPassword(Long userId, ReqModifyUserPasswordDto dto) throws NotFoundException, AuthorityException {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userMapper.findById(userId);

        if(user == null) {
            throw new NotFoundException("해당 사용자를 찾을 수 없습니다.");
        }

        if(user.getId() != principalUser.getId()) {
            throw new AuthorityException("권한이 없습니다.");
        }

        userMapper.modifyPassword(dto.toEntity(userId, passwordEncoder));

        return true;
    }

    @Transactional(rollbackFor = SignupException.class)
    public Boolean insertAdminAndUserRoles(ReqSignupDto dto) throws SignupException {
        User user = null;
        try {
            user = dto.toEntity(passwordEncoder, defaultProfileImg);
            userMapper.save(user);

            Role role = roleMapper.findByName("ROLE_ADMIN");

            if (role == null) {
                role = Role.builder().name("ROLE_ADMIN").build();
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

        return true;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteUser(Long userId) throws NotFoundException, AuthorityException {
        try {
            PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            User user = userMapper.findById(userId);

            if(user == null) {
                throw new NotFoundException("해당 사용자를 찾을 수 없습니다.");
            }

            if(user.getId() != principalUser.getId()) {
                throw new AuthorityException("권한이 없습니다.");
            }

            userMapper.deleteById(userId);
            userRolesMapper.deleteByUserId(userId);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return true;
    }

}