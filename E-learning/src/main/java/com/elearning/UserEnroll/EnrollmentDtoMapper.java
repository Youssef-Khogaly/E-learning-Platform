package com.elearning.UserEnroll;

import com.elearning.Courses.CourseDtoMapper;
import com.elearning.entities.Course;
import com.elearning.entities.UserEnrollment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor
public class EnrollmentDtoMapper {
    private final CourseDtoMapper courseDtoMapper;
    public EnrollmentDTO from(UserEnrollment userEnrollment){
        Objects.requireNonNull(userEnrollment);
        return EnrollmentDTO.builder().courseDTO(courseDtoMapper.form(userEnrollment.getCourse()))
                .enrollDate(userEnrollment.getEnrollDate()).build();
    }
}
