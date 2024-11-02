package com.project.doctor_fish_back.service.admin;

import com.project.doctor_fish_back.dto.admin.response.depart.RespDepartListDto;
import com.project.doctor_fish_back.entity.Depart;
import com.project.doctor_fish_back.exception.ExecutionException;
import com.project.doctor_fish_back.repository.admin.AdminDepartMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminDepartService {

    @Autowired
    private AdminDepartMapper adminDepartMapper;

    public List<RespDepartListDto> getDeparts() {
        try {
            List<Depart> departs = adminDepartMapper.departList();
            List<RespDepartListDto> dtos = new ArrayList<>();

            for(Depart depart : departs) {
                RespDepartListDto dto = RespDepartListDto.builder()
                        .id(depart.getId())
                        .name(depart.getName())
                        .build();
                dtos.add(dto);
            }
            return dtos;
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }
}


