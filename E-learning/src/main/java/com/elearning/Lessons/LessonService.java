package com.elearning.Lessons;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class LessonService {
    private final LessonJpaRepo lessonJpaRepo;


    Collection<Lesson> findAll(Long courseId , Integer sectionId)
    {
        return lessonJpaRepo.findAllByCourseAndSection(courseId,sectionId);
    }

}
