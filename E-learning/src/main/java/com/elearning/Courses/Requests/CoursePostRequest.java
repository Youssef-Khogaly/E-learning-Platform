package com.elearning.Courses.Requests;

import com.elearning.util.Money;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CoursePostRequest(@NotNull @NotBlank String title, @NotNull @NotBlank String desc, @NotNull @Valid Money price) {
}
