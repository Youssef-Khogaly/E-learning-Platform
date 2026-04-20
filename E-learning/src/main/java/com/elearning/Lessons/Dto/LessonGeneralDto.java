package com.elearning.Lessons.Dto;

import com.elearning.Lessons.LessonType;

public record LessonGeneralDto(Integer id, Integer index, LessonType type, String title,boolean isPreview, boolean accessible) {
}
