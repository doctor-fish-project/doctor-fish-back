package com.project.doctor_fish_back.aspect;

import com.project.doctor_fish_back.dto.request.comment.ReqModifyCommentDto;
import com.project.doctor_fish_back.dto.request.leave.ReqModifyLeaveDto;
import com.project.doctor_fish_back.entity.*;
import com.project.doctor_fish_back.exception.AuthorityException;
import com.project.doctor_fish_back.repository.*;
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

    @Autowired
    private AnnouncementMapper announcementMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private LeaveMapper leaveMapper;
    @Autowired
    private ReservationMapper reservationMapper;
    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private ReviewLikeMapper reviewLikeMapper;
    @Autowired
    private UserMapper userMapper;

    @Pointcut("@annotation(com.project.doctor_fish_back.aspect.annotation.AuthorityAop)")
    private void pointCut() {}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();

        switch (proceedingJoinPoint.getSignature().getName()) {
            case "modifyAnnounce":
            case "deleteAnnounce":
                announce(args);
                break;
            case "modifyComment":
                modifyComment(args);
                break;
            case "deleteComment":
                deleteComment(args);
                break;
            case "modifyLeave":
                modifyLeave(args);
                break;
            case "getReservationToUser":
            case "deleteReservationFromUser":
                reservation(args);
                break;
            case "modifyReview":
            case "deleteReview":
                review(args);
                break;
            case "dislike":
                dislike(args);
                break;
            case "modifyUser":
            case "modifyUserEmail":
            case "modifyUserPassword":
            case "deleteUser":
                user(args);
                break;
        }

        return proceedingJoinPoint.proceed();
    }

    private void announce(Object[] args) throws AuthorityException {
        for(Object arg : args) {
            if(arg.getClass() == Long.class) {
                Long announceId = (Long) arg;

                PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

                Announcement announcement = announcementMapper.findById(announceId);

                if(announcement.getUserId() != principalUser.getId()) {
                    throw new AuthorityException("권한이 없습니다.");
                }
            }
        }
    }
    private void modifyComment(Object[] args) throws AuthorityException {
        for(Object arg : args) {
            if(arg.getClass() == ReqModifyCommentDto.class) {
                ReqModifyCommentDto dto = (ReqModifyCommentDto) arg;

                PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

                Comment comment = commentMapper.findById(dto.getId());

                if(comment.getUserId() != principalUser.getId()) {
                    throw new AuthorityException("권한이 없습니다.");
                }
            }
        }
    }

    private void deleteComment(Object[] args) throws AuthorityException {
        for(Object arg : args) {
            if(arg.getClass() == Long.class) {
                Long commentId = (Long) arg;

                PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

                Comment comment = commentMapper.findById(commentId);

                if(comment.getUserId() != principalUser.getId()) {
                    throw new AuthorityException("권한이 없습니다.");
                }
            }
        }
    }

    private void modifyLeave(Object[] args) throws AuthorityException {
        Long leaveId = 0l;
        ReqModifyLeaveDto dto = null;

        for(Object arg : args) {
            if(arg.getClass() == Long.class) {
                leaveId = (Long) arg;
            }
            if(arg.getClass() == ReqModifyLeaveDto.class) {
                dto = (ReqModifyLeaveDto) arg;
            }
            Leave leave = leaveMapper.findLeaveById(leaveId);

            if(leave.getDoctorId() != dto.getDoctorId()) {
                throw new AuthorityException("권한이 없습니다.");
            }
        }
    }

    private void reservation(Object[] args) throws AuthorityException {
        for(Object arg : args) {
            if(arg.getClass() == Long.class) {
                Long reservationId = (Long) arg;

                PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

                Reservation reservation = reservationMapper.findById(reservationId);

                if(reservation.getUserId() != principalUser.getId()) {
                    throw new AuthorityException("권한이 없습니다.");
                }
            }
        }
    }

    private void review(Object[] args) throws AuthorityException {
        for(Object arg : args) {
            if(arg.getClass() == Long.class) {
                Long reviewId = (Long) arg;

                PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

                Review review = reviewMapper.findById(reviewId);

                if(review.getUserId() != principalUser.getId()) {
                    throw new AuthorityException("권한이 없습니다.");
                }
            }
        }
    }

    private void dislike(Object[] args) throws AuthorityException {
        for(Object arg : args) {
            if(arg.getClass() == Long.class) {
                Long reviewLikeId = (Long) arg;

                PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

                ReviewLike reviewLike = reviewLikeMapper.findById(reviewLikeId);

                if(reviewLike.getUserId() != principalUser.getId()) {
                    throw new AuthorityException("권한이 없습니다.");
                }
            }
        }
    }

    private void user(Object[] args) throws AuthorityException {
        for(Object arg : args) {
            if(arg.getClass() == Long.class) {
                Long userId = (Long) arg;

                PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

                User user = userMapper.findById(userId);

                if(user.getId() != principalUser.getId()) {
                    throw new AuthorityException("권한이 없습니다.");
                }
            }
        }
    }

}
