package com.project.doctor_fish_back.service.admin;

import com.project.doctor_fish_back.dto.admin.request.leave.ReqModifyLeaveDto;
import com.project.doctor_fish_back.dto.admin.request.leave.ReqRegisterLeaveDto;
import com.project.doctor_fish_back.dto.admin.response.leave.RespGetLeaveDto;
import com.project.doctor_fish_back.dto.admin.response.leave.RespGetLeaveListDto;
import com.project.doctor_fish_back.entity.Leave;
import com.project.doctor_fish_back.exception.ExecutionException;
import com.project.doctor_fish_back.repository.admin.AdminLeaveMapper;
import com.project.doctor_fish_back.security.principal.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminLeaveService {

    @Autowired
    private AdminLeaveMapper leaveMapper;

    public Boolean registerLeave(ReqRegisterLeaveDto dto) {
        try {
            leaveMapper.save(dto.toEntity());
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
        return true;
    }

    public RespGetLeaveListDto getLeavesByUserId() {
        try {
            PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            List<Leave> leaves = leaveMapper.getLeavesByUserId(principalUser.getId());
            Long leaveCount = leaveMapper.getCountLeaveByUserId(principalUser.getId());

            return RespGetLeaveListDto.builder()
                    .leaves(leaves)
                    .leaveCount(leaveCount)
                    .build();
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }

    public RespGetLeaveListDto getLeaves() {
        try {
            List<Leave> leaves = leaveMapper.getLeaves();
            Long leaveCount = leaveMapper.getCountLeaves();

            return RespGetLeaveListDto.builder()
                    .leaves(leaves)
                    .leaveCount(leaveCount)
                    .build();
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }

    public RespGetLeaveDto getLeaveToDoctorAndInfo(Long leaveId) {
        try {
            Leave leave = leaveMapper.findLeaveById(leaveId);

            return RespGetLeaveDto.builder()
                    .id(leave.getId())
                    .doctorId(leave.getUserId())
                    .status(leave.getStatus())
                    .leaveDate(leave.getLeaveDate())
                    .endDate(leave.getEndDate())
                    .registerDate(leave.getRegisterDate())
                    .updateDate(leave.getUpdateDate())
                    .userName(leave.getUserName())
                    .userImg(leave.getUserImg())
                    .departName(leave.getDepartName())
                    .build();
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }

    public Boolean modifyLeave(Long leaveId, ReqModifyLeaveDto dto) {
        try {
            leaveMapper.modify(dto.toEntity(leaveId));
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
        return true;
    }

    public Boolean acceptLeave(Long leaveId) {
        try {
            leaveMapper.acceptById(leaveId);
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
        return true;
    }

    public Boolean cancelLeave(Long leaveId) {
        try {
            leaveMapper.cancelById(leaveId);
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
        return true;
    }

    public Boolean deleteLeave(Long leaveId) {
        try {
            leaveMapper.deleteById(leaveId);
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
        return true;
    }

}
