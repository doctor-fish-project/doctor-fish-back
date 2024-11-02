package com.project.doctor_fish_back.service.admin;

import com.project.doctor_fish_back.dto.admin.request.auth.ReqAdminSigninDto;
import com.project.doctor_fish_back.dto.admin.request.auth.ReqAdminSignupDto;
import com.project.doctor_fish_back.dto.admin.request.reservation.ReqPageAndLimitDto;
import com.project.doctor_fish_back.dto.admin.request.user.ReqModifyAdminUsernameDto;
import com.project.doctor_fish_back.dto.admin.request.user.ReqModifyUserDto;
import com.project.doctor_fish_back.dto.admin.request.user.ReqModifyUserPasswordDto;
import com.project.doctor_fish_back.dto.admin.response.auth.RespSigninDto;
import com.project.doctor_fish_back.dto.admin.response.user.RespGetUserListDto;
import com.project.doctor_fish_back.dto.admin.response.user.RespMyInfoDto;
import com.project.doctor_fish_back.entity.*;
import com.project.doctor_fish_back.exception.ExecutionException;
import com.project.doctor_fish_back.exception.SigninException;
import com.project.doctor_fish_back.exception.SignupException;
import com.project.doctor_fish_back.repository.UserRolesMapper;
import com.project.doctor_fish_back.repository.admin.AdminDepartMapper;
import com.project.doctor_fish_back.repository.admin.AdminDoctorMapper;
import com.project.doctor_fish_back.repository.admin.AdminUserMapper;
import com.project.doctor_fish_back.security.jwt.JwtProvider;
import com.project.doctor_fish_back.security.principal.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdminUserService {

    @Value("${user.profile.user.img.default}")
    private String userDefaultProfileImg;

    @Value("${user.profile.doctor.img.default}")
    private String doctorDefaultProfileImg;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private AdminUserMapper userMapper;
    @Autowired
    private UserRolesMapper userRolesMapper;
    @Autowired
    private AdminDepartMapper departMapper;
    @Autowired
    private AdminDoctorMapper doctorMapper;
    private ContentNegotiatingViewResolver viewResolver;

    public Boolean isDuplicateEmail(String email) {
        try {
            return Optional.ofNullable(userMapper.findByEmail(email)).isPresent();
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }

    public Boolean isDuplicatePhoneNumber(String phoneNumber) {
        try {
            return Optional.ofNullable(userMapper.findByPhoneNumber(phoneNumber)).isPresent();
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }

    public RespSigninDto getGeneratedAccessToken(ReqAdminSigninDto dto) throws SigninException {
        try {
            User user = checkUsernameAndPassword(dto.getUsername(), dto.getPassword());

            return RespSigninDto.builder()
                    .expireDate(jwtProvider.getExpireDate().toLocaleString())
                    .accessToken(jwtProvider.generateAccessToken(user))
                    .build();
        } catch (SigninException e) {
            throw new SigninException(e.getMessage());
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

    public RespGetUserListDto getUserList(ReqPageAndLimitDto dto, String searchText) {
        try {
            Long startIndex = (dto.getPage() - 1) * dto.getLimit();
            List<User> users = userMapper.userList(startIndex, dto.getLimit(), searchText);
            Long userCount = userMapper.userCount(searchText);

            return RespGetUserListDto.builder()
                    .users(users)
                    .userCount(userCount)
                    .build();
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }

    public Boolean modifyUser(ReqModifyUserDto dto) {
        try {
            PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            User user = userMapper.findById(principalUser.getId());

            Set<String> roles = user.getUserRoles().stream().map(
                    userRole -> userRole.getRole().getPosition()
            ).collect(Collectors.toSet());

            if(roles.contains("ROLE_DOCTOR")) {
                if(dto.getImg() == null || dto.getImg().equals("")) {
                    dto.setImg(doctorDefaultProfileImg);
                }
                userMapper.modify(dto.toEntity(principalUser.getId()));
                Doctor doctor = doctorMapper.findByUserId(principalUser.getId());
                doctorMapper.modify(Doctor.builder()
                                .id(doctor.getId())
                                .comment(dto.getComment())
                                .record(dto.getRecord())
                                .build());

                return true;
            }

            if(dto.getImg() == null || dto.getImg().equals("")) {
                dto.setImg(userDefaultProfileImg);
            }

            userMapper.modify(dto.toEntity(principalUser.getId()));
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }

        return true;
    }

    public Boolean modifyAdminUsername(ReqModifyAdminUsernameDto dto) {
        try {
            PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            userMapper.modifyEmail(dto.toEntity(principalUser.getId()));
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
        return true;
    }

    public Boolean modifyUserPassword(ReqModifyUserPasswordDto dto) {
        try {
            PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            userMapper.modifyPassword(dto.toEntity( principalUser.getId(), passwordEncoder));
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
        return true;
    }

    // 관리자 유저 삭제
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

    private void insertInfoOrAdminAndUserRoles(ReqAdminSignupDto dto, User user) throws SignupException {
        try {
            user = dto.toEntity(passwordEncoder, userDefaultProfileImg);
            userMapper.save(user);

            userMapper.modifyEmailValidById(user.getId());

            UserRoles userRoles = UserRoles.builder()
                    .userId(user.getId())
                    .roleId(dto.getRoleId())
                    .build();

            userRolesMapper.save(userRoles);

            user.setUserRoles(Set.of(userRoles));
        } catch (Exception e) {
            throw new SignupException("로그인 도중 오류 발생");
        }
    }

    private void doctorSignup(ReqAdminSignupDto dto, User user) throws SignupException {
        try {
            if(dto.getDepartId() == null || dto.getDepartId() == 0) {
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

            Depart depart = departMapper.findById(dto.getDepartId());

//            if(depart == null) {
//                departMapper.save(Depart.builder().name(dto.getDepartName()).build());
//                depart = departMapper.findByName(dto.getDepartName());
//            }

            Doctor doctor = Doctor.builder()
                    .userId(user.getId())
                    .departId(depart.getId())
                    .comment(dto.getComment())
                    .record(dto.getRecord())
                    .build();

            doctorMapper.save(doctor);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SignupException("로그인 도중 오류 발생");
        }
    }

    public RespMyInfoDto getMyInfo() {
        try {
            PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userMapper.findById(principalUser.getId());
            Doctor doctor = doctorMapper.findByUserId(principalUser.getId());

            Set<Role> roles = user.getUserRoles().stream().map(
                    userRole -> userRole.getRole()
            ).collect(Collectors.toSet());
            if(doctor == null) {
                return RespMyInfoDto.builder()
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

            return RespMyInfoDto.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .name(user.getName())
                    .phoneNumber(user.getPhoneNumber())
                    .img(user.getImg())
                    .emailValid(user.getEmailValid())
                    .registerDate(user.getRegisterDate())
                    .updateDate(user.getUpdateDate())
                    .record(doctor.getRecord())
                    .comment(doctor.getComment())
                    .roles(roles)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }

}