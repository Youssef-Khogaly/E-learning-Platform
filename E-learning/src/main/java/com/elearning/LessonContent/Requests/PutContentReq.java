package com.elearning.LessonContent.Requests;

import com.elearning.Lessons.LessonType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PutContentReq(@NotNull LessonType type, @NotNull @NotBlank String content) {
}
