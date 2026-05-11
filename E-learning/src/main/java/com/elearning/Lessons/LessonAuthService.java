package com.elearning.Lessons;

import com.elearning.Courses.Course;
import com.elearning.Courses.CourseAuthorization;
import com.elearning.entities.UserEnrollment;
import com.elearning.entities.users.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LessonAuthService {

    private final CourseAuthorization courseAuthorization;
    // need to check if user is instructor before checking enrollment
    public boolean canRead(Lesson lesson, UserEnrollment userEnrollment)
    {
        if(lesson.getIsPreview())
            return true;
        // must be enrolled
        if(userEnrollment == null)
            return false;

        if(lesson.getStatus() == LessonStatus.PUBLISHED)
        {
            return true;
        }
        else if( lesson.getStatus() == LessonStatus.UNPUBLISHED)
        {
            if(lesson.getLastUnpublishedAt() == null)
                throw new NullPointerException("lesson unpublished, while unpublished date is null"); // safety, should never happen
            return userEnrollment.getEnrollDate().isBefore(lesson.getLastUnpublishedAt());
        }
        return false;
    }

    public boolean canWrite(User user , Course course , Lesson lesson)
    {
        return courseAuthorization.canWrite(user,course);
    }
}
