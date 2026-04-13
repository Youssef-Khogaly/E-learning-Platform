package com.elearning.Courses;

import com.elearning.Exceptions.UnAllowedStateTransitionException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class CourseStateTransitionService {

    private final CourseJpaRepo courseJpaRepo;
    private final CourseService courseService;

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
    public Course unPublishCourse(Long courseId){
        var course = courseService.findById(courseId);

        return unPublishCourse(course);
    }
    public Course unPublishCourse(Course course){
        var currState = course.getState();
        if(currState == CourseState.UNPUBLISHED || currState == CourseState.ARCHIVED || currState == CourseState.DRAFT)
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
    public Course archiveCourse(Course course){
        var currState = course.getState();

        if(currState == CourseState.DRAFT)
            throw new UnAllowedStateTransitionException("can not archive draft course, you can delete it");
        else if(currState == CourseState.PUBLISHED)
            throw new UnAllowedStateTransitionException("can not archive  published course, unpublish it first");
        else if (currState == CourseState.ARCHIVED)
            throw new UnAllowedStateTransitionException("course is already archived");
        else if (currState == CourseState.UNPUBLISHED)
        {
            course.setUnPublishedAt(Instant.now());
            course.setState(CourseState.ARCHIVED);
        }

        return course;
    }
    public Course archiveCourse(Long courseId){
        var course = courseService.findById(courseId);

        return archiveCourse(course);
    }


}
