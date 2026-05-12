package com.elearning.LessonContent.dto;

import com.elearning.Lessons.LessonType;
import lombok.Builder;

@Builder
public record LessonContentDto(long id ,int duration ,LessonType type,Object content) {
}
