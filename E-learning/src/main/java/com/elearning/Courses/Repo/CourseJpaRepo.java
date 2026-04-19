package com.elearning.Courses.Repo;

import com.elearning.Courses.Course;
import com.elearning.Courses.CourseState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CourseJpaRepo extends JpaRepository<Course,Long> {


    @Query("select exists (select 1 from Course  c where  c.id = :courseId and c.instructor.id = :instructorId)")
    boolean isInstructor(Long instructorId , Long courseId);

    Optional<Course> findByIdAndState(Long id, CourseState status);

    Page<Course> findAllByState(CourseState State, Pageable pageable);
    Page<Course> findAllByInstructor_IdAndState(Long id,CourseState state,Pageable pageable);


}
