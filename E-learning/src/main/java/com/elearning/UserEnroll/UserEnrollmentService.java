package com.elearning.UserEnroll;

import com.elearning.Courses.CourseJpaRepo;
import com.elearning.Courses.EnCourseSortBy;
import com.elearning.Videos.EnSortDir;
import com.elearning.entities.UserEnrollment;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserEnrollmentService implements IUserEnrollmentService{

    private final CourseJpaRepo courseJpaRepo;
    private final UserEnrollmentJpaRepo enrollmentJpaRepo;
    @Override
    public boolean isEnrolled(Long userId, Long courseId) {
        return enrollmentJpaRepo.isEnrolled(userId,courseId);
    }
    public Page<UserEnrollment> findAllEnrollement(Long usrId , int size , int page , EnEnrollmentSortBy sortBy , EnSortDir sortDir){
        var sort = Sort.by(sortDir.toDirection(), sortBy.toString());
        var pageReq = PageRequest.of(page,size,sort);
        return enrollmentJpaRepo.findAllByUser_Id(usrId,pageReq);
    }
}
