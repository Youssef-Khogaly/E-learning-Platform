package com.elearning.Lessons.Requests;

import com.elearning.Lessons.LessonType;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record LessonPostRequest(@NotNull LessonType type, @NotNull @NotBlank String title ,
                                @NotNull @Positive Integer index , @NotNull Boolean isPreview, @Nullable  String text , @Nullable String videoId) {
}
