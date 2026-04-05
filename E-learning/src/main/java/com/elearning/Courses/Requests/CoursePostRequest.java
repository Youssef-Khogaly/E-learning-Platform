package com.elearning.Courses.Requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CoursePostRequest(@NotNull @NotBlank String title, @NotNull @NotBlank String desc, @NotNull @Positive Long price) {
}
