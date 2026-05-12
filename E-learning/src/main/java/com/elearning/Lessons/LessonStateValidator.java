package com.elearning.Lessons;

import com.elearning.Exceptions.BadRequestException;
import org.springframework.stereotype.Service;

@Service
public class LessonStateValidator {


    public boolean isContentUpdatable(final Lesson lesson)
    {
        return lesson.getState() == LessonState.DRAFT;
    }
    public void canUpdateContent(final Lesson lesson)
    {
        if(!isContentUpdatable(lesson))
            throw new BadRequestException("Lesson must be draft to be updatable");
    }

}
