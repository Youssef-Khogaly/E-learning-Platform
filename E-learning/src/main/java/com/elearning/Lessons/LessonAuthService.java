package com.elearning.Lessons;

import com.elearning.Courses.Course;
import com.elearning.Courses.CourseAuthorization;
import com.elearning.entities.UserEnrollment;
import com.elearning.entities.users.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class LessonAuthService {

    private final CourseAuthorization courseAuthorization;
    // need to check if user is instructor before checking enrollment
    boolean canRead(Lesson lesson, UserEnrollment userEnrollment)
    {
        if(lesson.getIsPreview())
            return true;
        if(lesson.getStatus() == LessonStatus.PUBLISHED)
        {
            return true;
        }
        else if( lesson.getStatus() == LessonStatus.UNPUBLISHED)
        {
            return userEnrollment.getEnrollDate().isBefore(lesson.getLast_unpublished_timeStamp());
        }
        return false;
    }

    boolean canWrite(User user , Course course , Lesson lesson)
    {
        return courseAuthorization.canWrite(user,course);
    }
}
