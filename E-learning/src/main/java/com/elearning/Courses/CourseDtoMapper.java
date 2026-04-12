package com.elearning.Courses;

import com.elearning.Users.UserDtoMappers;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor
public class CourseDtoMapper {
    private final UserDtoMappers userDtoMappers;

    public CourseDTO form(Course course){
        Objects.requireNonNull(course);
        Objects.requireNonNull(course.getInstructor());

        return CourseDTO.builder().id(course.getId()).title(course.getTitle())
                .desc(course.getDesc()).status(course.getStatus()).price(course.getPrice())
                .instructor(userDtoMappers.form(course.getInstructor())).build();
    }
}
