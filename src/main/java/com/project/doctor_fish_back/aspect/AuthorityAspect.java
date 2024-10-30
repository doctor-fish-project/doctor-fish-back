package com.project.doctor_fish_back.aspect;

import com.project.doctor_fish_back.dto.admin.request.comment.ReqModifyCommentDto;
import com.project.doctor_fish_back.dto.admin.request.leave.ReqModifyLeaveDto;
import com.project.doctor_fish_back.entity.*;
import com.project.doctor_fish_back.exception.AuthorityException;
import com.project.doctor_fish_back.repository.RoleMapper;
import com.project.doctor_fish_back.repository.admin.*;
import com.project.doctor_fish_back.security.principal.PrincipalUser;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthorityAspect {

    @Pointcut("@annotation(com.project.doctor_fish_back.aspect.annotation.AuthorityAop)")
    private void pointCut() {}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!(principal instanceof PrincipalUser)) {
            throw new AuthorityException("권한이 없습니다.");
        }

        return proceedingJoinPoint.proceed();
    }
}

