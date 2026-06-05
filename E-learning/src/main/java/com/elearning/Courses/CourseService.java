package com.elearning.Courses;

import com.elearning.Courses.Repo.CourseJpaRepo;
import com.elearning.Exceptions.BadRequestException;
import com.elearning.Exceptions.NotFoundException;
import com.elearning.Exceptions.UnAllowedStateTransitionException;
import com.elearning.Exceptions.UnAuthorizedException;
import com.elearning.Security.services.AuthenticationService;
import com.elearning.UserEnroll.UserEnrollmentJpaRepo;
import com.elearning.Users.UserJpaRepo;
import com.elearning.Videos.EnSortDir;
import com.elearning.entities.users.User;
import com.elearning.entities.users.UserRoles;
import com.elearning.util.Money;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CourseService {
    private final CourseJpaRepo courseJpaRepo;
    private final UserEnrollmentJpaRepo userEnrollmentJpaRepo;
    private final UserJpaRepo userJpaRepo;
    private final CourseAuthorization courseAuthorization;
    private final CourseStateTransitionService courseStateTransitionService;

    public Course findById(Long id){
        return courseJpaRepo.findById(id).orElseThrow(() -> new NotFoundException("Course with id:" + id + " does not exist" ));
    }
    public Course findById(Long id , CourseState state){
        return courseJpaRepo.findByIdAndState(id,state).orElseThrow(() -> new NotFoundException("Course with id:" + id + " does not exist" ));
    }
    public Course findByIdForUser(Long courseId)
    {
            var course = findById(courseId);
            if(courseAuthorization.canRead(course))
                return course;

            throw new UnAuthorizedException("Course access is not allowed");
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
    public Course create(String title , String desc , Money price){
        var course = new Course();
        course.setState(CourseState.DRAFT);
        var user = new User(); // fetch from security context later
        user.setId(1L);
        user.setRole(UserRoles.Instructor);
        if(user.getRole() == UserRoles.Student)
            throw new UnAuthorizedException("Students are not allowed to create courses");
        course.setInstructor(user);
        course.setTitle(title);
        course.setDesc(desc);
        course.setPrice(price);
        return courseJpaRepo.save(course);
    }
    @Transactional
    public Course updateState(Long courseId , CourseState newState)
    {
        var course = findById(courseId);

        if(!courseAuthorization.canWrite(course))
        {
            throw new UnAuthorizedException("Access denied");
        }
        return switch (newState) {
            case DRAFT -> throw new UnAllowedStateTransitionException("course is already draft or already published");
            case PUBLISHED -> courseStateTransitionService.publishCourse(course);
            case UNPUBLISHED -> courseStateTransitionService.unPublishCourse(course);
        };
    }
    public Course save(Course course){
        return courseJpaRepo.save(course);
    }
    public boolean isExists(Long courseId) {
        return courseJpaRepo.existsById(courseId);
    }
    public boolean hasAnyEnroll(Long courseId){
        return userEnrollmentJpaRepo.hasAnyEnroll(courseId);
    }
}
