package com.project.doctor_fish_back.service.admin;

import com.project.doctor_fish_back.aspect.annotation.AuthorityAop;
import com.project.doctor_fish_back.aspect.annotation.NotFoundAop;
import com.project.doctor_fish_back.dto.admin.request.leave.ReqModifyLeaveDto;
import com.project.doctor_fish_back.dto.admin.request.leave.ReqRegisterLeaveDto;
import com.project.doctor_fish_back.dto.admin.response.leave.RespGetLeaveDto;
import com.project.doctor_fish_back.dto.admin.response.leave.RespGetLeaveListDto;
import com.project.doctor_fish_back.entity.Leave;
import com.project.doctor_fish_back.repository.admin.AdminLeaveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminLeaveService {

    @Autowired
    private AdminLeaveMapper leaveMapper;

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

    @NotFoundAop
    public RespGetLeaveDto getLeaveToDoctorAndInfo(Long leaveId) {
        Leave leave = leaveMapper.findLeaveById(leaveId);

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

    @NotFoundAop
    @AuthorityAop
    public Boolean modifyLeave(Long leaveId, ReqModifyLeaveDto dto) {
        leaveMapper.modify(dto.toEntity(leaveId));
        return true;
    }

    @NotFoundAop
    public Boolean acceptLeave(Long leaveId) {
        leaveMapper.acceptById(leaveId);
        return true;
    }

    @NotFoundAop
    public Boolean cancelLeave(Long leaveId) {
        leaveMapper.cancelById(leaveId);
        return true;
    }

    @NotFoundAop
    public Boolean deleteLeave(Long leaveId) {
        leaveMapper.deleteById(leaveId);
        return true;
    }

}
