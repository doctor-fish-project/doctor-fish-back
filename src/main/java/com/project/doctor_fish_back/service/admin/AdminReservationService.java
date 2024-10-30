package com.project.doctor_fish_back.service.admin;

import com.project.doctor_fish_back.aspect.annotation.NotFoundAop;
import com.project.doctor_fish_back.dto.admin.request.reservation.ReqPageAndLimitDto;
import com.project.doctor_fish_back.dto.admin.response.reservation.RespGetReservationDto;
import com.project.doctor_fish_back.dto.admin.response.reservation.RespGetReservationListDto;
import com.project.doctor_fish_back.dto.admin.response.reservation.RespMonthReservationsCountByDoctorsDto;
import com.project.doctor_fish_back.dto.search.ReqSearchDto;
import com.project.doctor_fish_back.entity.*;
import com.project.doctor_fish_back.exception.AuthorityException;
import com.project.doctor_fish_back.exception.ExecutionException;
import com.project.doctor_fish_back.repository.MonthMapper;
import com.project.doctor_fish_back.repository.UserRolesMapper;
import com.project.doctor_fish_back.repository.admin.AdminAlarmInsertMapper;
import com.project.doctor_fish_back.repository.admin.AdminDoctorMapper;
import com.project.doctor_fish_back.repository.admin.AdminReservationMapper;
import com.project.doctor_fish_back.repository.user.UserAlarmMapper;
import com.project.doctor_fish_back.security.principal.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class AdminReservationService {

    @Autowired
    private AdminReservationMapper reservationMapper;

    @Autowired
    private AdminDoctorMapper adminDoctorMapper;

    @Autowired
    private UserRolesMapper userRolesMapper;

    @Autowired
    private MonthMapper monthMapper;

    @Autowired
    private AdminAlarmInsertMapper alarmInsertMapper;

    @NotFoundAop
    public Boolean acceptReservation(Long reservationId) {
        try {
            reservationMapper.acceptById(reservationId);
            Reservation reservation = reservationMapper.findById(reservationId);

            alarmInsertMapper.save(AlarmInsert.builder()
                            .typeId(1L)
                            .alarmId(reservationId)
                            .messageId(1L)
                            .build());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExecutionException("실행 도중 오류 발생");
        }
        return true;
    }

    @NotFoundAop
    public Boolean cancelReservation(Long reservationId) {
        try {
            reservationMapper.cancelById(reservationId);
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
        return true;
    }

    // 대쉬보드 월 별 예약 수
    public RespMonthReservationsCountByDoctorsDto getReservationCountMonth() {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = principalUser.getId();

        if (userRolesMapper.findRoleIdByUserId(userId) == 3) {
            Doctor doctor = adminDoctorMapper.findByUserId(userId);
            List<Map<String, Object>> reservations = reservationMapper.getMonthCountsByDoctorId(doctor.getId());
            List<Month> months = monthMapper.getAll();

            return RespMonthReservationsCountByDoctorsDto.builder()
                    .reservations(reservations)
                    .months(months)
                    .build();
        }

        List<Map<String, Object>> reservations = reservationMapper.getMonthCountsByDoctors();
        List<Month> months = monthMapper.getAll();

        return RespMonthReservationsCountByDoctorsDto.builder()
                .reservations(reservations)
                .months(months)
                .build();
    }

    // 대쉬보드 전체 예약
    public RespGetReservationListDto getDashBoardReservations() {
        Long limit = 6L;

        try {
            PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Long userId = principalUser.getId();

            if (userRolesMapper.findRoleIdByUserId(userId) == 3) {
                Doctor doctor = adminDoctorMapper.findByUserId(userId);
                return RespGetReservationListDto.builder()
                        .reservations(reservationMapper.getDashBoardReservationsByDoctorId(doctor.getId(), limit))
                        .build();
            }

            return RespGetReservationListDto.builder()
                    .reservations(reservationMapper.getAllByLimit(limit))
                    .build();
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }

    // 대쉬보드 오늘 예약
    public RespGetReservationListDto getDashBoardReservationsToday() {
        Long limit = 6L;

        try {
            PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Long userId = principalUser.getId();

            if (userRolesMapper.findRoleIdByUserId(userId) == 3) {
                Doctor doctor = adminDoctorMapper.findByUserId(userId);
                return RespGetReservationListDto.builder()
                        .reservations(reservationMapper.getDashBoardTodayReservationsByDoctorId(doctor.getId(), limit))
                        .build();
            }

            return RespGetReservationListDto.builder()
                    .reservations(reservationMapper.getTodayByLimit(limit))
                    .build();
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }
    
    // 관리자 페이지 전체 예약
    public RespGetReservationListDto getReservations(ReqPageAndLimitDto dto, String searchText) {
        try {
            Long startIndex = (dto.getPage() - 1) * dto.getLimit();
            PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Long userId = principalUser.getId();

            if (userRolesMapper.findRoleIdByUserId(userId) == 3) {
                Doctor doctor = adminDoctorMapper.findByUserId(userId);
                return RespGetReservationListDto.builder()
                        .reservations(reservationMapper.getReservationsByDoctorId(doctor.getId(), startIndex, dto.getLimit(), searchText))
                        .totalCount(reservationMapper.getCountReservationsByDoctorId(doctor.getId(), searchText))
                        .build();
            }

            List<Reservation> reservations = reservationMapper.getReservations(startIndex, dto.getLimit(), searchText);
            Long totalCount = reservationMapper.getCountReservations(searchText);

            return RespGetReservationListDto.builder()
                    .reservations(reservations)
                    .totalCount(totalCount)
                    .build();
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }

    // 관리자 페이지 오늘 예약
    public RespGetReservationListDto getReservationsToday(ReqPageAndLimitDto dto, String searchText) {
        try {
            Long startIndex = (dto.getPage() - 1) * dto.getLimit();
            PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Long userId = principalUser.getId();

            if (userRolesMapper.findRoleIdByUserId(userId) == 3) {
                Doctor doctor = adminDoctorMapper.findByUserId(userId);
                return RespGetReservationListDto.builder()
                        .reservations(reservationMapper.getTodayReservationsByDoctorId(doctor.getId(), startIndex, dto.getLimit(), searchText))
                        .totalCount(reservationMapper.getCountTodayReservationsByDoctorId(doctor.getId(), searchText))
                        .build();
            }
            List<Reservation> reservations = reservationMapper.getTodayReservations(startIndex, dto.getLimit(), searchText);
            Long totalCount = reservationMapper.getCountTodayReservations(searchText);

            return RespGetReservationListDto.builder()
                    .reservations(reservations)
                    .totalCount(totalCount)
                    .build();
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }

    @NotFoundAop
    public Boolean deleteReservationFromAdmin (Long reservationId) throws AuthorityException {
        try {
            Reservation reservation = reservationMapper.findById(reservationId);

            if (reservation.getStatus() != 3) {
                throw new AuthorityException("취소된 예약만 삭제할 수 있습니다.");
            }

            reservationMapper.deleteById(reservationId);
        } catch (AuthorityException e) {
            throw new AuthorityException(e.getMessage());
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
        return true;
    }

}

