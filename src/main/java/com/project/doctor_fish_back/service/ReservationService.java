package com.project.doctor_fish_back.service;

import com.project.doctor_fish_back.dto.request.leave.ReqModifyLeaveDto;
import com.project.doctor_fish_back.dto.request.reservation.ReqPageAndLimitDto;
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

    public Boolean acceptReservation(Long reservationId) throws NotFoundException {
        findReservationById(reservationId);

        reservationMapper.acceptById(reservationId);

        return true;
    }

    public Boolean cancelReservation(Long reservationId) throws NotFoundException {
        findReservationById(reservationId);

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

    public RespGetReservationDto getReservationToUser(Long reservationId) throws AuthorityException, NotFoundException {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Reservation reservation = findReservationById(reservationId);

        if(reservation.getUserId() != principalUser.getId()) {
            throw new AuthorityException("권한이 없습니다.");
        }

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



    public RespGetReservationDto getReservationToInfoAndDoctor(Long reservationId) throws NotFoundException {
        Reservation reservation = findReservationById(reservationId);

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

    public Boolean deleteReservationFromUser(Long reservationId) throws NotFoundException, AuthorityException {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Reservation reservation = findReservationById(reservationId);

        if(reservation.getUserId() != principalUser.getId()) {
            throw new AuthorityException("권한이 없습니다.");
        }

        if(reservation.getStatus() != 3) {
            throw new AuthorityException("취소된 예약만 삭제할 수 있습니다.");
        }

        reservationMapper.deleteById(reservationId);
        return true;
    }

    public Boolean deleteReservationFromAdmin(Long reservationId) throws NotFoundException, AuthorityException {
        Reservation reservation = findReservationById(reservationId);

        if(reservation.getStatus() != 3) {
            throw new AuthorityException("취소된 예약만 삭제할 수 있습니다.");
        }

        reservationMapper.deleteById(reservationId);
        return true;
    }

    private Reservation findReservationById(Long reservationId) throws NotFoundException {
        Reservation reservation = reservationMapper.findById(reservationId);

        if(reservation == null) {
            throw new NotFoundException("해당 예약을 찾을 수 없습니다.");
        }

        return reservation;
    }

}