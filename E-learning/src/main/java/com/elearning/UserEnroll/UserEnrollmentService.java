package com.elearning.UserEnroll;

import com.elearning.Courses.Repo.CourseJpaRepo;
import com.elearning.Videos.EnSortDir;
import com.elearning.entities.UserEnrollment;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserEnrollmentService implements IUserEnrollmentService{

    private final CourseJpaRepo courseJpaRepo;
    private final UserEnrollmentJpaRepo enrollmentJpaRepo;
    @Override
    public boolean isEnrolled(Long userId, Long courseId) {
        return enrollmentJpaRepo.isEnrolled(userId,courseId);
    }
    public Optional<UserEnrollment> findByUserAndCourse(Long usrId,Long courseId)
    {
        return enrollmentJpaRepo.findByUser_IdAndCourse_Id(usrId,courseId);
    }
    public Page<UserEnrollment> findAllEnrollement(Long usrId , int size , int page , EnEnrollmentSortBy sortBy , EnSortDir sortDir){
        var sort = Sort.by(sortDir.toDirection(), sortBy.toString());
        var pageReq = PageRequest.of(page,size,sort);
        return enrollmentJpaRepo.findAllByUser_Id(usrId,pageReq);
    }
    public boolean isCourseHasAnyEnroll(Long courseId){
        return enrollmentJpaRepo.hasAnyEnroll(courseId);
    }
}
