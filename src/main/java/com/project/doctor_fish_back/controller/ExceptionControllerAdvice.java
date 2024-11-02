package com.project.doctor_fish_back.controller;

import com.project.doctor_fish_back.exception.*;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(ValidException.class)
    public ResponseEntity<?> validException(ValidException e) {
        return ResponseEntity.badRequest().body(e.getFieldErrors());
    }

    @ExceptionHandler(AccessTokenValidException.class)
    public ResponseEntity<?> accessTokenValidException(AccessTokenValidException e) {
        return ResponseEntity.status(403).body(false);
    }

    @ExceptionHandler(SignupException.class)
    public ResponseEntity<?> signupException(SignupException e) {
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

    @ExceptionHandler(SigninException.class)
    public ResponseEntity<?> signinException(SigninException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(EmailValidException.class)
    public ResponseEntity<?> emailValidException(EmailValidException e) {
        return ResponseEntity.status(401).body(e.getMessage());
    }

    @ExceptionHandler(AuthorityException.class)
    public ResponseEntity<?> authorityException(AuthorityException e) {
        return ResponseEntity.status(401).body(e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundException(NotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(ReviewLikeException.class)
    public ResponseEntity<?> reviewLikeException(ReviewLikeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ExecutionException.class)
    public ResponseEntity<?> executionException(ExecutionException e) {
        return ResponseEntity.status(500).body(e.getMessage());
    }

}
