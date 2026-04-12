package com.elearning.Courses;

import com.elearning.Exceptions.NotFoundException;
import com.elearning.UserEnroll.IUserEnrollmentService;
import com.elearning.UserEnroll.UserEnrollmentJpaRepo;
import com.elearning.Videos.EnSortDir;
import com.elearning.entities.Course;
import com.elearning.entities.CourseStatus;
import com.elearning.entities.UserEnrollment;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourseService {
    private final CourseJpaRepo courseJpaRepo;
    private final UserEnrollmentJpaRepo userEnrollmentJpaRepo;
    public Course findById(Long id , CourseStatus status){
        return courseJpaRepo.findByIdAndStatus(id,status).orElseThrow(() -> new NotFoundException("Course with id:" + id + " does not exist" ));
    }
    public Course findById(Long id){
        return courseJpaRepo.findById(id).orElseThrow(() -> new NotFoundException("Course with id:" + id + " does not exist" ));
    }
    public Page<Course> findAll(CourseStatus status , int size , int page , EnCourseSortBy sortBy , EnSortDir sortDir){
        var sort = Sort.by(sortDir.toDirection(),sortBy.toString());
        var pageable = PageRequest.of(page,size,sort);
        return  courseJpaRepo.findAllByStatus(CourseStatus.PUBLISHED,pageable);
    }

    public Page<Course> findAllforInstructor(Long inst_id,int size , int page , EnCourseSortBy sortBy , EnSortDir sortDir){
        var sort = Sort.by(sortDir.toDirection(),sortBy.toString());
        var pageable = PageRequest.of(page,size,sort);
        return  courseJpaRepo.findAllByInstructor_Id(inst_id,pageable);
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
