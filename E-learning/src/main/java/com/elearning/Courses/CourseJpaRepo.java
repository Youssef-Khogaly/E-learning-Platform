package com.elearning.Courses;

import com.elearning.entities.Course;
import com.elearning.entities.CourseStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CourseJpaRepo extends JpaRepository<Course,Long> {


    @Query("select exists (select 1 from Course  c where  c.id = :courseId and c.instructor.id = :instructorId)")
    boolean isInstructor(Long instructorId , Long courseId);

    Optional<Course> findByIdAndStatus(Long id, CourseStatus status);

    Page<Course> findAllByStatus(CourseStatus status, Pageable pageable);
    Page<Course> findAllByInstructor_Id(Long id,Pageable pageable);

}
