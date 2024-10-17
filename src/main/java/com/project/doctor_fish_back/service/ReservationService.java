package com.project.doctor_fish_back.service;

import com.project.doctor_fish_back.aspect.annotation.AuthorityAop;
import com.project.doctor_fish_back.aspect.annotation.NotFoundAop;
import com.project.doctor_fish_back.dto.request.leave.ReqModifyLeaveDto;
import com.project.doctor_fish_back.dto.request.reservation.ReqPageAndLimitDto;
import com.project.doctor_fish_back.dto.request.reservation.ReqModifyReservationDto;
import com.project.doctor_fish_back.dto.request.reservation.ReqRegisterReservationDto;
import com.project.doctor_fish_back.dto.response.reservation.RespGetReservationCountMonth;
import com.project.doctor_fish_back.dto.response.reservation.RespGetReservationDto;
import com.project.doctor_fish_back.dto.response.reservation.RespGetReservationListDto;
import com.project.doctor_fish_back.entity.*;
import com.project.doctor_fish_back.exception.AuthorityException;
import com.project.doctor_fish_back.repository.MonthMapper;
import com.project.doctor_fish_back.repository.ReservationMapper;
import com.project.doctor_fish_back.security.principal.PrincipalUser;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class ReservationService {

    @Autowired
    private ReservationMapper reservationMapper;

    @Autowired
    private MonthMapper monthMapper;

    public Boolean registerReservation(ReqRegisterReservationDto dto) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Reservation reservation = dto.toEntity(principalUser.getId());
        reservationMapper.register(reservation);

        return true;
    }

    @NotFoundAop
    public Boolean acceptReservation(Long reservationId) {
        reservationMapper.acceptById(reservationId);
        return true;
    }

    @NotFoundAop
    public Boolean cancelReservation(Long reservationId) {
        reservationMapper.cancelById(reservationId);
        return true;
    }

    public RespGetReservationListDto getReservations(ReqPageAndLimitDto dto) {
        Long startIndex = (dto.getPage() - 1) * dto.getLimit();
        List<Reservation> reservations = reservationMapper.getAll(startIndex, dto.getLimit());
        Long totalCount = reservationMapper.getCountAll();

        return RespGetReservationListDto.builder()
                .reservations(reservations)
                .totalCount(totalCount)
                .build();
    }

    public RespGetReservationListDto getReservationsToday(ReqPageAndLimitDto dto) {
        Long startIndex = (dto.getPage() - 1) * dto.getLimit();
        List<Reservation> reservations = reservationMapper.getToday(startIndex, dto.getLimit());
        Long totalCount = reservationMapper.getCountToday();

        return RespGetReservationListDto.builder()
                .reservations(reservations)
                .totalCount(totalCount)
                .build();
    }

    public RespGetReservationListDto getDashBoardReservations() {
        Long limit = 6L;

        return RespGetReservationListDto.builder()
                .reservations(reservationMapper.getAllByLimit(limit))
                .build();
    }

    public RespGetReservationListDto getDashBoardReservationsToday() {
        Long limit = 6L;

        return RespGetReservationListDto.builder()
                .reservations(reservationMapper.getTodayByLimit(limit))
                .build();
    }

    public RespGetReservationListDto getReservationsToUser() {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Reservation> reservations = reservationMapper.getToUser(principalUser.getId());
        Long totalCount = reservationMapper.getCountToUser(principalUser.getId());

        return RespGetReservationListDto.builder()
                .reservations(reservations)
                .totalCount(totalCount)
                .build();
    }

    public RespGetReservationListDto getAllReservationsToUser() {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Reservation> reservations = reservationMapper.getAllToUser(principalUser.getId());
        Long totalCount = reservationMapper.getCountAllToUser(principalUser.getId());

        return RespGetReservationListDto.builder()
                .reservations(reservations)
                .totalCount(totalCount)
                .build();
    }

    @NotFoundAop
    @AuthorityAop
    public RespGetReservationDto getReservationToUser(Long reservationId) {
        Reservation reservation = reservationMapper.findById(reservationId);

        return RespGetReservationDto.builder()
                .id(reservation.getId())
                .userId(reservation.getUserId())
                .doctorId(reservation.getDoctorId())
                .status(reservation.getStatus())
                .reservationDate(reservation.getReservationDate())
                .registerDate(reservation.getReservationDate())
                .doctor(reservation.getDoctor())
                .build();
    }

    public RespGetReservationListDto getAllReservationsToDoctor(Long doctorId) {
        List<Reservation> reservations = reservationMapper.getAllToDoctor(doctorId);
        Long totalCount = reservationMapper.getCountAllToDoctor(doctorId);

        return RespGetReservationListDto.builder()
                .reservations(reservations)
                .totalCount(totalCount)
                .build();
    }

    public RespGetReservationListDto getAllReservationsToInfo() {
        List<Reservation> reservations = reservationMapper.getAllToInfo();
        Long totalCount = reservationMapper.getCountAllToInfo();

        return RespGetReservationListDto.builder()
                .reservations(reservations)
                .totalCount(totalCount)
                .build();
    }

    public RespGetReservationCountMonth getAllReservationsMonth(String year) {
        List<Month> months = monthMapper.getAll();
        List<GetReservationMonthDoctors> doctors = reservationMapper.getDoctors(year);
        List<GetReservationMonth> reserveList = new ArrayList<>();

        for(GetReservationMonthDoctors doctor : doctors) {
            List<Integer> data = reservationMapper.getCounts(doctor.getId());

            GetReservationMonth reserve = GetReservationMonth.builder()
                    .label(doctor.getName())
                    .data(data)
                    .build();
            reserveList.add(reserve);

        }
        return RespGetReservationCountMonth.builder()
                .months(months)
                .getReservationMonths(reserveList)
                .build();
    }

    @NotFoundAop
    public RespGetReservationDto getReservationToInfoAndDoctor(Long reservationId) {
        Reservation reservation = reservationMapper.findById(reservationId);

        return RespGetReservationDto.builder()
                .id(reservation.getId())
                .userId(reservation.getUserId())
                .doctorId(reservation.getDoctorId())
                .status(reservation.getStatus())
                .reservationDate(reservation.getReservationDate())
                .registerDate(reservation.getReservationDate())
                .doctor(reservation.getDoctor())
                .build();
    }

    @NotFoundAop
    @AuthorityAop
    public Boolean modifyReservation(Long reservationId, ReqModifyReservationDto dto) {
        reservationMapper.modify(dto.toEntity(reservationId));
        return true;
    }

    @NotFoundAop
    @AuthorityAop
    public Boolean deleteReservationFromUser(Long reservationId) {
        reservationMapper.deleteById(reservationId);
        return true;
    }

    @NotFoundAop
    public Boolean deleteReservationFromAdmin (Long reservationId) throws AuthorityException {
        Reservation reservation = reservationMapper.findById(reservationId);

        if (reservation.getStatus() != 3) {
            throw new AuthorityException("취소된 예약만 삭제할 수 있습니다.");
        }

        reservationMapper.deleteById(reservationId);
        return true;
    }

}

