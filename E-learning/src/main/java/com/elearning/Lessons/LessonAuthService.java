package com.elearning.Lessons;

import com.elearning.Courses.Course;
import com.elearning.Courses.CourseAuthorization;
import com.elearning.Exceptions.UnAuthorizedException;
import com.elearning.Security.services.AuthenticationService;
import com.elearning.entities.UserEnrollment;
import com.elearning.entities.users.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LessonAuthService {

    private final CourseAuthorization courseAuthorization;
    public boolean canRead(final User user,final Course course,final Lesson lesson, final UserEnrollment userEnrollment)
    {
        if(user == null)
            return false;
        if(lesson.getIsPreview())
            return true;
        // lesson owner
        if(course.getInstructor().getId().equals(user.getId()))
            return true;

        // must be enrolled
        if(userEnrollment == null)
            return false;

        if(lesson.getState() == LessonState.PUBLISHED)
        {
            return true;
        }
        else if( lesson.getState() == LessonState.UNPUBLISHED)
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
    public void canReadOrThrow(final User user,final Course course,final Lesson lesson, final UserEnrollment userEnrollment)
    {
        if(!canRead(user,course,lesson,userEnrollment))
            throw new UnAuthorizedException("Not Authorized");
    }

    public void canWriteOrThrow(User user , Course course , Lesson lesson)
    {
        if(!canWrite(user,course,lesson))
            throw new UnAuthorizedException("Not Authorized");
    }



    /// ////////


    public boolean canRead(final Course course,final Lesson lesson, final UserEnrollment userEnrollment)
    {
        var user = AuthenticationService.getCurrentUser().orElse(null);
        if (user == null)
            return false;
        if(lesson.getIsPreview())
            return true;
        // lesson owner
        if(course.getInstructor().getId().equals(user.getId()))
            return true;

        // must be enrolled
        if(userEnrollment == null)
            return false;

        if(lesson.getState() == LessonState.PUBLISHED)
        {
            return true;
        }
        else if( lesson.getState() == LessonState.UNPUBLISHED)
        {
            if(lesson.getLastUnpublishedAt() == null)
                throw new NullPointerException("lesson unpublished, while unpublished date is null"); // safety, should never happen
            return userEnrollment.getEnrollDate().isBefore(lesson.getLastUnpublishedAt());
        }
        return false;
    }

    public boolean canWrite(final Course course , final Lesson lesson)
    {
        return courseAuthorization.canWrite(course);
    }
    public void canReadOrThrow(final Course course,final Lesson lesson, final UserEnrollment userEnrollment)
    {
        if(!canRead(course,lesson,userEnrollment))
            throw new UnAuthorizedException("Not Authorized");
    }

    public void canWriteOrThrow(final Course course , final Lesson lesson)
    {
        if(!canWrite(course,lesson))
            throw new UnAuthorizedException("Not Authorized");
    }
}
