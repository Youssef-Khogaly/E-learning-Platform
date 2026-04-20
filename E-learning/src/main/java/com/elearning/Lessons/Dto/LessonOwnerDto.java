package com.elearning.Lessons.Dto;

import com.elearning.Lessons.LessonStatus;
import com.elearning.Lessons.LessonType;

import java.time.Instant;

public record LessonOwnerDto(LessonGeneralDto lessonGeneralDto , LessonStatus status , Instant last_published_timeStamp,Instant last_unpublished_timeStamp) {
}
