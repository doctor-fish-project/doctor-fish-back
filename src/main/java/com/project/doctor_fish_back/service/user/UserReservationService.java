package com.project.doctor_fish_back.service.user;

import com.project.doctor_fish_back.aspect.annotation.AuthorityAop;
import com.project.doctor_fish_back.aspect.annotation.NotFoundAop;
import com.project.doctor_fish_back.dto.user.response.reservation.RespGetReservationDto;
import com.project.doctor_fish_back.dto.user.response.reservation.RespGetReservationListDto;
import com.project.doctor_fish_back.dto.user.request.reservation.ReqModifyReservationDto;
import com.project.doctor_fish_back.dto.user.request.reservation.ReqRegisterReservationDto;
import com.project.doctor_fish_back.entity.Reservation;
import com.project.doctor_fish_back.repository.user.UserReservationMapper;
import com.project.doctor_fish_back.security.principal.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserReservationService {

    @Autowired
    private UserReservationMapper reservationMapper;

    public Boolean registerReservation(ReqRegisterReservationDto dto) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Reservation reservation = dto.toEntity(principalUser.getId());
        reservationMapper.register(reservation);

        return true;
    }

    @NotFoundAop
    public Boolean cancelReservation(Long reservationId) {
        reservationMapper.cancelById(reservationId);
        return true;
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

}

