package com.project.doctor_fish_back.service;

import com.project.doctor_fish_back.dto.response.category.RespCategoryDto;
import com.project.doctor_fish_back.entity.Category;
import com.project.doctor_fish_back.repository.CategoryMapper;
import com.project.doctor_fish_back.repository.UserRolesMapper;
import com.project.doctor_fish_back.security.principal.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private UserRolesMapper userRolesMapper;


    public List<RespCategoryDto> getCategory() {
//        PrincipalUser user = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Long roleId = (long) userRolesMapper.findRoleIdByUserId(user.getId());
            Long roleId = 2L;
        return categoryMapper.getCategory(roleId).stream().map(Category::toDto).collect(Collectors.toList());
    }
}
