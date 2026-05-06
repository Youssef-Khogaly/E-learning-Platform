package com.elearning.Lessons.mappers;


import com.elearning.Lessons.Dto.LessonDto;
import com.elearning.Lessons.Lesson;
import org.springframework.stereotype.Component;

@Component
public class LessonDtoMapper {



    public LessonDto from(Lesson lesson, boolean isAccessible)
    {
        return LessonDto.builder().id(lesson.getId())
                .index(lesson.getIndex())
                .type(lesson.getType())
                .title(lesson.getTitle())
                .isPreview(lesson.getIsPreview())
                .accessible(isAccessible)
                .build();
    }
}
