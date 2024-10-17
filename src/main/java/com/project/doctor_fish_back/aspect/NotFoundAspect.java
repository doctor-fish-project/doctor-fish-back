package com.project.doctor_fish_back.aspect;

import com.project.doctor_fish_back.dto.request.comment.ReqModifyCommentDto;
import com.project.doctor_fish_back.dto.request.comment.ReqRegisterCommentDto;
import com.project.doctor_fish_back.entity.*;
import com.project.doctor_fish_back.repository.*;
import org.apache.ibatis.javassist.NotFoundException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NotFoundAspect {

    @Autowired
    private AnnouncementMapper announcementMapper;
    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private ReviewLikeMapper reviewLikeMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private DoctorMapper doctorMapper;
    @Autowired
    private LeaveMapper leaveMapper;
    @Autowired
    private ReservationMapper reservationMapper;
    @Autowired
    private UserMapper userMapper;

    @Pointcut("@annotation(com.project.doctor_fish_back.aspect.annotation.NotFoundAop)")
    private void pointCut() {}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();

        switch (proceedingJoinPoint.getSignature().getName()) {
            case "modifyAnnounce":
            case "deleteAnnounce":
            case "getAnnouncement":
                announce(args);
                break;
            case "writeComment":
                writeComment(args);
                break;
            case "getComments":
                getComments(args);
                break;
            case "modifyComment":
                modifyComment(args);
                break;
            case "deleteComment":
                deleteComment(args);
                break;
            case "modifyDoctor":
            case "modifyDoctorUsername":
            case "modifyDoctorPassword":
            case "deleteDoctor":
                doctor(args);
                break;
            case "getLeaveToDoctorAndInfo":
            case "modifyLeave":
            case "acceptLeave":
            case "cancelLeave":
            case "deleteLeave":
                leave(args);
                break;
            case "acceptReservation":
            case "cancelReservation":
            case "getReservationToUser":
            case "getReservationToInfoAndDoctor":
            case "deleteReservationFromUser":
            case "deleteReservationFromAdmin":
                reservation(args);
                break;
            case "modifyReview":
            case "deleteReview":
            case "like":
            case "getLikeCount":
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

    private void announce(Object[] args) throws NotFoundException {
        for(Object arg : args) {
            if(arg.getClass() == Long.class) {
                Long announceId = (Long) arg;

                Announcement announcement = announcementMapper.findById(announceId);

                if(announcement == null) {
                    throw new NotFoundException("해당 공지사항을 찾을 수 없습니다.");
                }
            }
        }
    }

    private void writeComment(Object[] args) throws NotFoundException {
        for(Object arg : args) {
            if(arg.getClass() == ReqRegisterCommentDto.class) {
                ReqRegisterCommentDto dto = (ReqRegisterCommentDto) arg;

                Review review = reviewMapper.findById(dto.getReviewId());

                if(review == null) {
                    throw new NotFoundException("해당 리뷰를 찾을 수 없습니다.");
                }
            }
        }
    }

    private void getComments(Object[] args) throws NotFoundException {
        for(Object arg : args) {
            if(arg.getClass() == Long.class) {
                Long reviewId = (Long) arg;

                Review review = reviewMapper.findById(reviewId);

                if(review == null) {
                    throw new NotFoundException("해당 리뷰를 찾을 수 없습니다.");
                }
            }
        }
    }

    private void modifyComment(Object[] args) throws NotFoundException {
        for(Object arg : args) {
            if(arg.getClass() == ReqModifyCommentDto.class) {
                ReqModifyCommentDto dto = (ReqModifyCommentDto) arg;

                Comment comment = commentMapper.findById(dto.getId());

                if(comment == null) {
                    throw new NotFoundException("해당 댓글을 찾을 수 없습니다.");
                }
            }
        }
    }

    private void deleteComment(Object[] args) throws NotFoundException {
        for(Object arg : args) {
            if(arg.getClass() == Long.class) {
                Long commentId = (Long) arg;

                Comment comment = commentMapper.findById(commentId);

                if(comment == null) {
                    throw new NotFoundException("해당 댓글을 찾을 수 없습니다.");
                }
            }
        }
    }

    private void doctor(Object[] args) throws NotFoundException {
        for(Object arg : args) {
            if(arg.getClass() == Long.class) {
                Long doctorId = (Long) arg;

                Doctor doctor = doctorMapper.findById(doctorId);

                if(doctor == null) {
                    throw new NotFoundException("해당 사용자를 찾을 수 없습니다.");
                }
            }
        }
    }

    private void leave(Object[] args) throws NotFoundException {
        for(Object arg : args) {
            if(arg.getClass() == Long.class) {
                Long leaveId = (Long) arg;

                Leave leave = leaveMapper.findLeaveById(leaveId);

                if(leave == null) {
                    throw new NotFoundException("해당 연차를 찾을 수 없습니다.");
                }
            }
        }
    }

    private void reservation(Object[] args) throws NotFoundException {
        for(Object arg : args) {
            if(arg.getClass() == Long.class) {
                Long reservationId = (Long) arg;

                Reservation reservation = reservationMapper.findById(reservationId);

                if(reservation == null) {
                    throw new NotFoundException("해당 예약을 찾을 수 없습니다.");
                }
            }
        }
    }

    private void review(Object[] args) throws NotFoundException {
        for(Object arg : args) {
            if(arg.getClass() == Long.class) {
                Long reviewId = (Long) arg;

                Review review = reviewMapper.findById(reviewId);

                if(review == null) {
                    throw new NotFoundException("해당 리뷰를 찾을 수 없습니다.");
                }
            }
        }
    }

    private void dislike(Object[] args) throws NotFoundException {
        for(Object arg : args) {
            if(arg.getClass() == Long.class) {
                Long reviewLikeId = (Long) arg;

                ReviewLike reviewLike = reviewLikeMapper.findById(reviewLikeId);

                if(reviewLike == null) {
                    throw new NotFoundException("좋아요를 취소할 수 없습니다.");
                }
            }
        }
    }

    private void user(Object[] args) throws NotFoundException {
        for(Object arg : args) {
            if(arg.getClass() == Long.class) {
                Long userId = (Long) arg;

                User user = userMapper.findById(userId);

                if(user == null) {
                    throw new NotFoundException("해당 사용자를 찾을 수 없습니다.");
                }
            }
        }
    }

}
