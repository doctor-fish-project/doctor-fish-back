package com.project.doctor_fish_back.service.admin;

import com.project.doctor_fish_back.aspect.annotation.NotFoundAop;
import com.project.doctor_fish_back.dto.admin.request.reservation.ReqPageAndLimitDto;
import com.project.doctor_fish_back.dto.admin.response.reservation.RespGetReservationCountMonth;
import com.project.doctor_fish_back.dto.admin.response.reservation.RespGetReservationDto;
import com.project.doctor_fish_back.dto.admin.response.reservation.RespGetReservationListDto;
import com.project.doctor_fish_back.dto.search.ReqSearchDto;
import com.project.doctor_fish_back.entity.*;
import com.project.doctor_fish_back.exception.AuthorityException;
import com.project.doctor_fish_back.exception.ExecutionException;
import com.project.doctor_fish_back.repository.admin.AdminMonthMapper;
import com.project.doctor_fish_back.repository.admin.AdminReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AdminReservationService {

    @Autowired
    private AdminReservationMapper reservationMapper;

    @Autowired
    private AdminMonthMapper monthMapper;

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

    public RespGetReservationListDto getAllReservationsToDoctor(Long doctorId) {
        try {
            List<Reservation> reservations = reservationMapper.getAllToDoctor(doctorId);
            Long totalCount = reservationMapper.getCountAllToDoctor(doctorId);

            return RespGetReservationListDto.builder()
                    .reservations(reservations)
                    .totalCount(totalCount)
                    .build();
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }

    public RespGetReservationListDto getAllReservationsToInfo() {
        try {
            List<Reservation> reservations = reservationMapper.getAllToInfo();
            Long totalCount = reservationMapper.getCountAllToInfo();

            return RespGetReservationListDto.builder()
                    .reservations(reservations)
                    .totalCount(totalCount)
                    .build();
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }

    public RespGetReservationCountMonth getAllReservationsMonth(String year) {
        try {
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
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }

    @NotFoundAop
    public RespGetReservationDto getReservationToInfoAndDoctor(Long reservationId) {
        try {
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
            List<Reservation> reservations = reservationMapper.getBySearch(dto.getSearchText());
            Long totalCount = reservationMapper.getCountBySearch(dto.getSearchText());

            return RespGetReservationListDto.builder()
                    .reservations(reservations)
                    .totalCount(totalCount)
                    .build();
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }

}

