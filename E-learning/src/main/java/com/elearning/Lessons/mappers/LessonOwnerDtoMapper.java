package com.elearning.Lessons.mappers;

import com.elearning.Courses.Course;
import com.elearning.Lessons.Dto.LessonDto;
import com.elearning.Lessons.Lesson;
import com.elearning.entities.users.User;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.function.Predicate;

@Component
public class LessonOwnerDtoMapper implements LessonMapper{
    @Override
    public boolean supported(User user, Course course) {
        return  course.getInstructor().equals(user);
    }

    @Override
    public LessonDto from(Lesson lesson, boolean isAccessible) {

        return LessonDto.builder()
                .id(lesson.getId())
                .index(lesson.getIndex())
                .type(lesson.getType())
                .title(lesson.getTitle())
                .status(lesson.getStatus())
                .last_published_timeStamp(lesson.getGetLastPublishedAt())
                .last_unpublished_timeStamp(lesson.getGetLastUnpublishedAt())
                .isPreview(lesson.getIsPreview())
                .accessible(isAccessible)
                .build();
    }

    @Override
    public Collection<LessonDto> from(Collection<Lesson> lessonCollection, Predicate<Lesson> isAccessible) {
        return lessonCollection.stream().map(l -> this.from(l,isAccessible.test(l))).toList();
    }
}
