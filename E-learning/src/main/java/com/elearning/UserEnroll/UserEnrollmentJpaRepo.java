package com.elearning.UserEnroll;

import com.elearning.entities.UserEnrollment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserEnrollmentJpaRepo extends JpaRepository<UserEnrollment,Long> {

    @Query("select exists (select 1  from UserEnrollment r where r.user.id = :usrId and r.course.id = :courseId)")
    boolean isEnrolled(Long usrId , Long courseId);

    Page<UserEnrollment> findAllByUser_Id(Long userId , Pageable pageable);
    @Query("select exists (select 1 from UserEnrollment  r where r.course.id = : courseId)")
    boolean hasAnyEnroll(Long courseId);
}
