package com.project.doctor_fish_back.service;

import com.project.doctor_fish_back.dto.request.leave.ReqModifyLeaveDto;
import com.project.doctor_fish_back.dto.request.leave.ReqRegisterLeaveDto;
import com.project.doctor_fish_back.dto.response.leave.RespGetLeaveDto;
import com.project.doctor_fish_back.dto.response.leave.RespGetLeaveListDto;
import com.project.doctor_fish_back.entity.Leave;
import com.project.doctor_fish_back.exception.AuthorityException;
import com.project.doctor_fish_back.repository.LeaveMapper;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {

    @Autowired
    private LeaveMapper leaveMapper;

    public Boolean registerLeave(ReqRegisterLeaveDto dto) {
        leaveMapper.save(dto.toEntity());
        return true;
    }

    public RespGetLeaveListDto getAllLeavesToDoctor(Long doctorId) {
        List<Leave> leaves = leaveMapper.getAllToDoctor(doctorId);
        Long leaveCount = leaveMapper.getCountAllToDoctor(doctorId);

        return RespGetLeaveListDto.builder()
                .leaves(leaves)
                .leaveCount(leaveCount)
                .build();
    }

    public RespGetLeaveListDto getAllLeavesToInfo() {
        List<Leave> leaves = leaveMapper.getAllToInfo();
        Long leaveCount = leaveMapper.getCountAllToInfo();

        return RespGetLeaveListDto.builder()
                .leaves(leaves)
                .leaveCount(leaveCount)
                .build();
    }

    public RespGetLeaveDto getLeaveToDoctorAndInfo(Long leaveId) throws NotFoundException {
        Leave leave = leaveMapper.findLeaveById(leaveId);

        if(leave == null) {
            throw new NotFoundException("해당 연차를 찾을 수 없습니다.");
        }

        return RespGetLeaveDto.builder()
                .id(leave.getId())
                .doctorId(leave.getDoctorId())
                .status(leave.getStatus())
                .leaveDate(leave.getLeaveDate())
                .endDate(leave.getEndDate())
                .registerDate(leave.getRegisterDate())
                .updateDate(leave.getUpdateDate())
                .userName(leave.getUserName())
                .userImg(leave.getUserImg())
                .departName(leave.getDepartName())
                .build();
    }

    public Boolean modifyLeave(Long leaveId, ReqModifyLeaveDto dto) throws NotFoundException, AuthorityException {
        Leave leave = leaveMapper.findLeaveById(leaveId);

        if(leave == null) {
            throw new NotFoundException("해당 연차를 찾을 수 없습니다.");
        }

        if(leave.getDoctorId() != dto.getDoctorId()) {
            throw new AuthorityException("권한이 없습니다.");
        }

        leaveMapper.modify(dto.toEntity(leaveId));

        return true;
    }

    public Boolean acceptLeave(Long leaveId) throws NotFoundException {
        Leave leave = leaveMapper.findLeaveById(leaveId);

        if(leave == null) {
            throw new NotFoundException("해당 연차를 찾을 수 없습니다.");
        }

        leaveMapper.acceptById(leaveId);

        return true;
    }

    public Boolean cancelLeave(Long leaveId) throws NotFoundException {
        Leave leave = leaveMapper.findLeaveById(leaveId);

        if(leave == null) {
            throw new NotFoundException("해당 연차를 찾을 수 없습니다.");
        }

        leaveMapper.cancelById(leaveId);

        return true;
    }

    public Boolean deleteLeave(Long leaveId) throws NotFoundException {
        Leave leave = leaveMapper.findLeaveById(leaveId);

        if(leave == null) {
            throw new NotFoundException("해당 연차를 찾을 수 없습니다.");
        }

        leaveMapper.deleteById(leaveId);

        return true;
    }

}
