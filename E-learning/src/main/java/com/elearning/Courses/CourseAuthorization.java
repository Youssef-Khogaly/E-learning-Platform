package com.elearning.Courses;

import com.elearning.Exceptions.UnAuthorizedException;
import com.elearning.UserEnroll.UserEnrollmentService;
import com.elearning.entities.users.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@AllArgsConstructor
@Service
public class CourseAuthorization {

    private final UserEnrollmentService userEnrollmentService;

    public boolean canRead(final User usr , final Course course){
        Objects.requireNonNull(course);
        // owner always has access
        if(usr != null && course.getInstructor().getId().equals(usr.getId()))
            return true;
        // draft is private , only owner
        if(course.getState() == CourseState.DRAFT)
            return  false;

        // course already published. anyone can access
        if(course.getState() == CourseState.PUBLISHED)
        {
            return  true;
        }
        // un published. only students who enrolled can access
        return usr!= null && userEnrollmentService.findByUserAndCourse(usr.getId(),course.getId()).isPresent();
    }
    public boolean canWrite(final User usr , final Course course){
        Objects.requireNonNull(usr);
        Objects.requireNonNull(course);
        return course.getInstructor().getId().equals(usr.getId());

    }
}
