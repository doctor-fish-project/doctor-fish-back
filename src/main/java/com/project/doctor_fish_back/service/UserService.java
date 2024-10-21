package com.project.doctor_fish_back.service;

import com.project.doctor_fish_back.aspect.annotation.AuthorityAop;
import com.project.doctor_fish_back.aspect.annotation.NotFoundAop;
import com.project.doctor_fish_back.dto.request.auth.ReqAdminSigninDto;
import com.project.doctor_fish_back.dto.request.auth.ReqAdminSignupDto;
import com.project.doctor_fish_back.dto.request.auth.ReqSigninDto;
import com.project.doctor_fish_back.dto.request.auth.ReqSignupDto;
import com.project.doctor_fish_back.dto.request.reservation.ReqPageAndLimitDto;
import com.project.doctor_fish_back.dto.request.user.ReqModifyAdminUsernameDto;
import com.project.doctor_fish_back.dto.request.user.ReqModifyUserDto;
import com.project.doctor_fish_back.dto.request.user.ReqModifyUserEmailDto;
import com.project.doctor_fish_back.dto.request.user.ReqModifyUserPasswordDto;
import com.project.doctor_fish_back.dto.response.auth.RespSigninDto;
import com.project.doctor_fish_back.dto.response.user.RespGetUserListDto;
import com.project.doctor_fish_back.dto.response.user.RespUserInfoDto;
import com.project.doctor_fish_back.entity.*;
import com.project.doctor_fish_back.exception.EmailValidException;
import com.project.doctor_fish_back.exception.SigninException;
import com.project.doctor_fish_back.exception.SignupException;
import com.project.doctor_fish_back.repository.*;
import com.project.doctor_fish_back.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Value("${user.profile.user.img.default}")
    private String userDefaultProfileImg;

    @Value("${user.profile.doctor.img.default}")
    private String doctorDefaultProfileImg;

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
    @Autowired
    private DepartMapper departMapper;
    @Autowired
    private DoctorMapper doctorMapper;
    private ContentNegotiatingViewResolver viewResolver;

    public Boolean isDuplicateEmail(String email) {
        try {
            return Optional.ofNullable(userMapper.findByEmail(email)).isPresent();
        } catch (Exception e) {
            throw new RuntimeException("실행 도중 오류가 발생했습니다.");
        }
    }

    public Boolean isDuplicatePhoneNumber(String phoneNumber) {
        try {
            return Optional.ofNullable(userMapper.findByPhoneNumber(phoneNumber)).isPresent();
        } catch (Exception e) {
            throw new RuntimeException("실행 도중 오류가 발생했습니다.");
        }
    }

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

    public RespSigninDto getGeneratedAccessToken(ReqAdminSigninDto dto) throws SigninException {
        User user = checkUsernameAndPassword(dto.getUsername(), dto.getPassword());

        return RespSigninDto.builder()
                .expireDate(jwtProvider.getExpireDate().toLocaleString())
                .accessToken(jwtProvider.generateAccessToken(user))
                .build();
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

    public RespGetUserListDto getUserList(ReqPageAndLimitDto dto) {
        Long startIndex = (dto.getPage() - 1) * dto.getLimit();
        List<User> users = userMapper.getAll(startIndex, dto.getLimit());
        Long userCount = userMapper.getCountAll();

        return RespGetUserListDto.builder()
                .users(users)
                .userCount(userCount)
                .build();
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
    public Boolean modifyAdminUsername(Long userId, ReqModifyAdminUsernameDto dto) {
        userMapper.modifyEmail(dto.toEntity(userId));
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

    @Transactional(rollbackFor = SignupException.class)
    public Boolean adminSignup(ReqAdminSignupDto dto) throws SignupException {
        User user = null;
        try {
            if (dto.getRoleId() == 2) { // 원무과
                insertInfoOrAdminAndUserRoles(dto, user);
            } else if (dto.getRoleId() == 3) { // 의사
                doctorSignup(dto, user);
            } else if (dto.getRoleId() == 4) { // 관리자
                insertInfoOrAdminAndUserRoles(dto, user);
            } else {
                throw new SignupException("잘못된 요청입니다.");
            }
        } catch (Exception e) {
            throw new SignupException(e.getMessage());
        }

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

    private void insertInfoOrAdminAndUserRoles(ReqAdminSignupDto dto, User user) {
        user = dto.toEntity(passwordEncoder, userDefaultProfileImg);
        userMapper.save(user);

        userMapper.modifyEmailValidById(user.getId());

        UserRoles userRoles = UserRoles.builder()
                .userId(user.getId())
                .roleId(dto.getRoleId())
                .build();

        userRolesMapper.save(userRoles);

        user.setUserRoles(Set.of(userRoles));
    }

    private void doctorSignup(ReqAdminSignupDto dto, User user) throws SignupException {
        if(dto.getDepartName() == null || dto.getDepartName().equals("")) {
            throw new SignupException("부서이름을 입력하세요.");
        }

        user = dto.toEntity(passwordEncoder, doctorDefaultProfileImg);
        userMapper.save(user);

        userMapper.modifyEmailValidById(user.getId());

        UserRoles userRoles = UserRoles.builder()
                .userId(user.getId())
                .roleId(dto.getRoleId())
                .build();

        userRolesMapper.save(userRoles);

        user.setUserRoles(Set.of(userRoles));

        Depart depart = departMapper.findByName(dto.getDepartName());

        if(depart == null) {
            departMapper.save(Depart.builder().name(dto.getDepartName()).build());
            depart = departMapper.findByName(dto.getDepartName());
        }

        Doctor doctor = Doctor.builder()
                .userId(user.getId())
                .departId(depart.getId())
                .comment(dto.getComment())
                .record(dto.getRecord())
                .build();

        doctorMapper.save(doctor);
    }

}