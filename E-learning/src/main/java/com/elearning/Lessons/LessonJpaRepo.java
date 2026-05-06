package com.elearning.Lessons;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface LessonJpaRepo extends JpaRepository<Lesson,Integer> {

    @Query("select l from Lesson l where l.section.id = :sectionId and l.section.course.id = :courseId")
    List<Lesson>findAllByCourseAndSection(Long courseId , Integer sectionId);
}
