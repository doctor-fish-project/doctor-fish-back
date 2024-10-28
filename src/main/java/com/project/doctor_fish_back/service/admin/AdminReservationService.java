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
import com.project.doctor_fish_back.repository.admin.AdminReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class AdminReservationService {

    @Autowired
    private AdminReservationMapper reservationMapper;

    @Autowired
    private MonthMapper monthMapper;

    @NotFoundAop
    public Boolean acceptReservation(Long reservationId) {
        try {
            reservationMapper.acceptById(reservationId);
        } catch (Exception e) {
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
        List<Map<String, Object>> reservations = reservationMapper.getMonthCountsByDoctors();
        List<Month> months = monthMapper.getAll();

        return RespMonthReservationsCountByDoctorsDto.builder()
                .reservations(reservations)
                .months(months)
                .build();
    }

    // 대쉬보드 전체 예약
    public RespGetReservationListDto getDashBoardReservations() {
        try {
            Long limit = 6L;

            return RespGetReservationListDto.builder()
                    .reservations(reservationMapper.getAllByLimit(limit))
                    .build();
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }

    // 대쉬보드 오늘 예약
    public RespGetReservationListDto getDashBoardReservationsToday() {
        try {
            Long limit = 6L;

            return RespGetReservationListDto.builder()
                    .reservations(reservationMapper.getTodayByLimit(limit))
                    .build();
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }
    
    // 관리자 페이지 전체 예약
    public RespGetReservationListDto getReservations(ReqPageAndLimitDto dto) {
        try {
            Long startIndex = (dto.getPage() - 1) * dto.getLimit();
            List<Reservation> reservations = reservationMapper.getAll(startIndex, dto.getLimit());
            Long totalCount = reservationMapper.getCountAll();

            return RespGetReservationListDto.builder()
                    .reservations(reservations)
                    .totalCount(totalCount)
                    .build();
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }

    // 관리자 페이지 오늘 예약
    public RespGetReservationListDto getReservationsToday(ReqPageAndLimitDto dto) {
        try {
            Long startIndex = (dto.getPage() - 1) * dto.getLimit();
            List<Reservation> reservations = reservationMapper.getToday(startIndex, dto.getLimit());
            Long totalCount = reservationMapper.getCountToday();

            return RespGetReservationListDto.builder()
                    .reservations(reservations)
                    .totalCount(totalCount)
                    .build();
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }

    public RespGetReservationListDto getReservationsByDoctorId(Long doctorId) {
        try {
            List<Reservation> reservations = reservationMapper.getReservationsByDoctorId(doctorId);
            Long totalCount = reservationMapper.getCountReservationsByDoctorId(doctorId);

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

    public RespGetReservationListDto searchReservation(ReqSearchDto dto) {
        try {
            List<Reservation> reservations = reservationMapper.getReservationsBySearch(dto.getSearchText());
            Long totalCount = reservationMapper.getCountReservationsBySearch(dto.getSearchText());

            return RespGetReservationListDto.builder()
                    .reservations(reservations)
                    .totalCount(totalCount)
                    .build();
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }

}

