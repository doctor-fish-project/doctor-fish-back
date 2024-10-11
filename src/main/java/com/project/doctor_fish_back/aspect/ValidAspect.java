package com.project.doctor_fish_back.aspect;

import com.project.doctor_fish_back.dto.request.auth.ReqSignupDto;
import com.project.doctor_fish_back.dto.request.user.ReqModifyUserPasswordDto;
import com.project.doctor_fish_back.exception.ValidException;
import com.project.doctor_fish_back.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;

@Aspect
@Component
public class ValidAspect {

    @Autowired
    private UserService userService;

    @Pointcut("@annotation(com.project.doctor_fish_back.aspect.annotation.ValidAop)")
    private void pointCut() {}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        BeanPropertyBindingResult bindingResult = null;

        for(Object arg : args) {
            if(arg.getClass() == BeanPropertyBindingResult.class) {
                bindingResult = (BeanPropertyBindingResult) arg;
                break;
            }
        }

        switch (proceedingJoinPoint.getSignature().getName()) {
            case "userSignup":
                validSignupDto(args, bindingResult);
                break;
            case "modifyUserPassword":
                validModifyUserPasswordDto(args, bindingResult);
                break;
        }

        if(bindingResult.hasErrors()) {
            throw new ValidException("유효성 검사 오류", bindingResult.getFieldErrors());
        }

        return proceedingJoinPoint.proceed();
    }

    private void validSignupDto(Object[] args, BeanPropertyBindingResult bindingResult) {
        for(Object arg : args) {
            if(arg.getClass() == ReqSignupDto.class) {
                ReqSignupDto dto = (ReqSignupDto) arg;

                if(!dto.getPassword().equals(dto.getCheckPassword())) {
                    FieldError fieldError = new FieldError("checkPassword", "checkPassword", "비밀번호가 일치하지 않습니다.");
                    bindingResult.addError(fieldError);
                }

                if(userService.isDuplicateEmail(dto.getEmail())) {
                    FieldError fieldError = new FieldError("email", "email", "이미 존재하는 이메일입니다.");
                    bindingResult.addError(fieldError);
                }
            }
        }
    }

    private void validModifyUserPasswordDto(Object[] args, BeanPropertyBindingResult bindingResult) {
        for(Object arg : args) {
            if(arg.getClass() == ReqModifyUserPasswordDto.class) {
                ReqModifyUserPasswordDto dto = (ReqModifyUserPasswordDto) arg;

                if(!dto.getPassword().equals(dto.getCheckPassword())) {
                    FieldError fieldError = new FieldError("checkPassword", "checkPassword", "비밀번호가 일치하지 않습니다.");
                    bindingResult.addError(fieldError);
                }
            }
        }
    }

}
