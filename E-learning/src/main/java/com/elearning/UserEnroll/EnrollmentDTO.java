package com.elearning.UserEnroll;

import com.elearning.Courses.CourseDTO;
import lombok.Builder;

import java.time.Instant;

@Builder
public record EnrollmentDTO(CourseDTO courseDTO, Instant enrollDate) {
}
