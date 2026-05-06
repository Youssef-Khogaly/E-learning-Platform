package com.elearning.Lessons;

import com.elearning.Lessons.mappers.LessonMapperResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class LessonService {
    private final LessonJpaRepo lessonJpaRepo;
    private final LessonMapperResolver lessonMapperResolver;
    private final LessonAuthService lessonAuthService;
    Collection<Lesson> findAll(Long courseId , Integer sectionId)
    {
        return lessonJpaRepo.findAllByCourseAndSection(courseId,sectionId);
    }

}
