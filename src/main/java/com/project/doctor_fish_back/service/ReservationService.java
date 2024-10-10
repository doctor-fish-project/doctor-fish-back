package com.project.doctor_fish_back.service;

import com.project.doctor_fish_back.dto.request.reservation.ReqRegisterReservationDto;
import com.project.doctor_fish_back.dto.response.reservation.RespGetReservationDto;
import com.project.doctor_fish_back.dto.response.reservation.RespGetReservationListDto;
import com.project.doctor_fish_back.entity.Reservation;
import com.project.doctor_fish_back.exception.AuthorityException;
import com.project.doctor_fish_back.repository.ReservationMapper;
import com.project.doctor_fish_back.security.principal.PrincipalUser;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReservationService {

    @Autowired
    private ReservationMapper reservationMapper;

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

    public RespGetReservationListDto getReservationsToUser() {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Reservation> reservations = reservationMapper.getToUser(principalUser.getId());
        int totalCount = reservationMapper.getCountAllToUser(principalUser.getId());

        return RespGetReservationListDto.builder()
                .reservations(reservations)
                .totalCount(totalCount)
                .build();
    }

    public RespGetReservationListDto getAllReservationsToUser() {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Reservation> reservations = reservationMapper.getAllToUser(principalUser.getId());
        int totalCount = reservationMapper.getCountToUser(principalUser.getId());

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

    public RespGetReservationListDto getAllReservationsToAdmin() {
        List<Reservation> reservations = reservationMapper.getAllToAdmin();
        int totalCount = reservationMapper.getCountAllToAdmin();

        return RespGetReservationListDto.builder()
                .reservations(reservations)
                .totalCount(totalCount)
                .build();
    }

    public RespGetReservationDto getReservationToAdmin(Long reservationId) throws NotFoundException {
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