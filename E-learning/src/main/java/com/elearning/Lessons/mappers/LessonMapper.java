package com.elearning.Lessons.mappers;

import com.elearning.Courses.Course;
import com.elearning.Lessons.Dto.LessonDto;
import com.elearning.Lessons.Lesson;
import com.elearning.entities.users.User;

import java.util.Collection;
import java.util.function.Predicate;

public interface LessonMapper {

    boolean supported(User user , Course  course);

    LessonDto from(Lesson lesson, boolean isAccessible);

    Collection<LessonDto> from(Collection<Lesson> lessonCollection, Predicate<Lesson> isAccessible);
}
