package com.elearning.Lessons.Dto;

import com.elearning.Lessons.LessonStatus;
import com.elearning.Lessons.LessonType;
import lombok.Builder;

import java.time.Instant;

@Builder
public record LessonDto(Integer id, Integer sectionId,Integer index, LessonType type, String title,
        LessonStatus status ,
                               Instant last_published_timeStamp,
                               Instant last_unpublished_timeStamp,
                               boolean isPreview,
                               boolean accessible) {
}
