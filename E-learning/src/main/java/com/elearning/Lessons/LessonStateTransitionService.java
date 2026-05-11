package com.elearning.Lessons;

import com.elearning.Exceptions.UnAllowedStateTransitionException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class LessonStateTransitionService {

    public Lesson publishLesson(Lesson lesson)
    {
        final LessonState currState = lesson.getState();
        if(currState == LessonState.DRAFT || currState == LessonState.UNPUBLISHED)
        {
            lesson.setState(LessonState.PUBLISHED);
            lesson.setLastPublishedAt(Instant.now());
        }else
        {
            throw new UnAllowedStateTransitionException("can not publish already published course");
        }

        return lesson;
    }

    public Lesson unPublishLesson(Lesson lesson)
    {
        var currState = lesson.getState();
        if(currState == LessonState.UNPUBLISHED)
        {
            throw new UnAllowedStateTransitionException("lesson is already unpublished");
        }
        else if (currState == LessonState.DRAFT)
        {
            throw new UnAllowedStateTransitionException("can not unpublish draft lesson");
        }
        else{
            lesson.setState(LessonState.UNPUBLISHED);
            lesson.setLastUnpublishedAt(Instant.now());
        }

        return lesson;
    }
}
