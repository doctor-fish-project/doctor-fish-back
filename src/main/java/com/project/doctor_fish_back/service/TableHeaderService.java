package com.project.doctor_fish_back.service;

import com.project.doctor_fish_back.entity.TableHeader;
import com.project.doctor_fish_back.repository.TableHeaderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableHeaderService {

    @Autowired
    private TableHeaderMapper tableHeaderMapper;

    public List<TableHeader> getTableHeaders(Long categoryId) {
        return tableHeaderMapper.getTableHeaders(categoryId);
    }
}
