package com.project.doctor_fish_back.service;

import com.project.doctor_fish_back.entity.TableHeader;
import com.project.doctor_fish_back.exception.ExecutionException;
import com.project.doctor_fish_back.repository.TableHeaderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableHeaderService {

    @Autowired
    private TableHeaderMapper tableHeaderMapper;

    public List<TableHeader> getTableHeaders(String pathName) {
        try {
            return tableHeaderMapper.tableheaderList(pathName);
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }
}
