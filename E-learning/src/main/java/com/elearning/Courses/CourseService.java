package com.elearning.Courses;

import com.elearning.Exceptions.NotFoundException;
import com.elearning.UserEnroll.UserEnrollmentJpaRepo;
import com.elearning.Videos.EnSortDir;
import com.elearning.entities.users.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourseService {
    private final CourseJpaRepo courseJpaRepo;
    private final UserEnrollmentJpaRepo userEnrollmentJpaRepo;
    public Course findById(Long id){
        return courseJpaRepo.findById(id).orElseThrow(() -> new NotFoundException("Course with id:" + id + " does not exist" ));
    }
    public Course findById(Long id , CourseState state){
        return courseJpaRepo.findByIdAndState(id,state).orElseThrow(() -> new NotFoundException("Course with id:" + id + " does not exist" ));
    }
    public Page<Course> findAll(CourseState state , int size , int page , EnCourseSortBy sortBy , EnSortDir sortDir){
        var sort = Sort.by(sortDir.toDirection(),sortBy.toString());
        var pageable = PageRequest.of(page,size,sort);
        return  courseJpaRepo.findAllByState(state,pageable);
    }

    public Page<Course> findAllForInstructorWithState
            (Long inst_id, CourseState state,int size , int page , EnCourseSortBy sortBy , EnSortDir sortDir){
        var sort = Sort.by(sortDir.toDirection(),sortBy.toString());
        var pageable = PageRequest.of(page,size,sort);

        return  courseJpaRepo.findAllByInstructor_IdAndState(inst_id,state,pageable);
    }
    public Course create(){
        var course = new Course();
        course.setState(CourseState.DRAFT);
        return course;
    }
    public Course save(Course course){
        return courseJpaRepo.save(course);
    }
    public boolean canEditCourse(Long usrId , Long courseId){
        return courseJpaRepo.isInstructor(usrId,courseId);
    }
    public boolean isExists(Long courseId) {
        return courseJpaRepo.existsById(courseId);
    }
    public boolean hasAnyEnroll(Long courseId){
        return userEnrollmentJpaRepo.hasAnyEnroll(courseId);
    }
}
