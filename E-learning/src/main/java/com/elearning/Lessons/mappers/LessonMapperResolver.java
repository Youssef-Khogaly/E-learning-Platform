package com.elearning.Lessons.mappers;

import com.elearning.Courses.Course;
import com.elearning.Lessons.Dto.LessonDto;
import com.elearning.Lessons.Lesson;
import com.elearning.Lessons.LessonAuthService;
import com.elearning.UserEnroll.UserEnrollmentService;
import com.elearning.entities.UserEnrollment;
import com.elearning.entities.users.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@AllArgsConstructor
public class LessonMapperResolver{


    private final List<LessonMapper> lessonMappers;
    private final LessonAuthService lessonAuthService;


    public Collection<LessonDto> resolve(final User user, final Course course, final Collection<Lesson> lesson , final UserEnrollment userEnrollment)
    {
        for(LessonMapper mapper: lessonMappers)
        {
            if(mapper.supported(user,course))
                return mapper.from(lesson,l -> lessonAuthService.canRead(user,course,l,userEnrollment));
        }

        throw new RuntimeException("can not map to lesson dto , unexpected exception");
    }
    public Collection<LessonDto> resolve(final Course course, final Collection<Lesson> lesson , final UserEnrollment userEnrollment)
    {
        for(LessonMapper mapper: lessonMappers)
        {
            if(mapper.supported(course))
                return mapper.from(lesson,l -> lessonAuthService.canRead(course,l,userEnrollment));
        }

        throw new RuntimeException("can not map to lesson dto , unexpected exception");
    }

}
