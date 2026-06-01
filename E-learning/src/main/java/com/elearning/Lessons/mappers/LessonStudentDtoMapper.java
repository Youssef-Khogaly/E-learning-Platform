package com.elearning.Lessons.mappers;


import com.elearning.Courses.Course;
import com.elearning.Lessons.Dto.LessonDto;
import com.elearning.Lessons.Lesson;
import com.elearning.entities.users.User;
import com.elearning.entities.users.UserRoles;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

@Component
public class LessonStudentDtoMapper implements LessonMapper {


    public LessonDto from(Lesson lesson, boolean isAccessible)
    {
        return LessonDto.builder().id(lesson.getId())
                .sectionId(lesson.getSection().getId())
                .index(lesson.getIndex())
                .type(lesson.getType())
                .title(lesson.getTitle())
                .isPreview(lesson.getIsPreview())
                .accessible(isAccessible)
                .build();
    }
    @Override
    public boolean supported(User user, Course course) {
        return user == null || user.getRole() == UserRoles.Student;
    }
    @Override
    public Collection<LessonDto> from(Collection<Lesson> lessonCollection, Predicate<Lesson> isAccessible) {
        return lessonCollection.stream()
                .map(l -> this.from(l, isAccessible.test(l))).toList();
    }

}
