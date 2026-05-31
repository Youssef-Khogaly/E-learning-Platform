package com.elearning.Lessons.Dto;

import com.elearning.Lessons.LessonState;
import com.elearning.Lessons.LessonType;
import lombok.Builder;

import java.time.Instant;

@Builder
public record LessonDto(Long id, Long sectionId,Integer index, LessonType type, String title,
        LessonState status ,
                               Instant last_published_timeStamp,
                               Instant last_unpublished_timeStamp,
                               boolean isPreview,
                               boolean accessible) {
}
