package com.elearning.Courses;

import com.elearning.Courses.Repo.CourseJpaRepo;
import com.elearning.Exceptions.UnAllowedStateTransitionException;
import com.elearning.UserEnroll.IUserEnrollmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@AllArgsConstructor
public class CourseStateTransitionService {

    private final CourseJpaRepo courseJpaRepo;
    private final CourseService courseService;
    private final IUserEnrollmentService userEnrollmentService;
    @Transactional
    public Course publishCourse(Long courseId){

        var course = courseService.findById(courseId);
        return publishCourse(course);
    }
    public Course publishCourse(Course course){
        if(course.getState() == CourseState.DRAFT || course.getState() == CourseState.UNPUBLISHED)
        {
            course.setState(CourseState.PUBLISHED);
            course.setUnPublishedAt(null);
            course.setPublishedAt(Instant.now());
        }else
        {
            throw new UnAllowedStateTransitionException("can not publish archived or already published course");
        }

        return course;
    }
    @Transactional
    public Course unPublishCourse(Long courseId){
        var course = courseService.findById(courseId);

        return unPublishCourse(course);
    }
    public Course unPublishCourse(Course course){
        var currState = course.getState();
        if(currState == CourseState.UNPUBLISHED)
        {
            throw new UnAllowedStateTransitionException("Course is already un published");
        }
        else if (currState == CourseState.DRAFT)
        {
            throw new UnAllowedStateTransitionException("can not unpublish archived or draft or  already unpublished course");
        }
        else{
            course.setState(CourseState.UNPUBLISHED);
            course.setUnPublishedAt(Instant.now());
            course.setPublishedAt(null);
        }

        return course;
    }

}
