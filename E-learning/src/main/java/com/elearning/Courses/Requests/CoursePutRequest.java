package com.elearning.Courses.Requests;

import com.elearning.entities.CourseStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CoursePutRequest(@NotNull @NotBlank String title, @NotNull CourseStatus status, @NotNull @NotBlank String desc, @Positive Long price){

}
