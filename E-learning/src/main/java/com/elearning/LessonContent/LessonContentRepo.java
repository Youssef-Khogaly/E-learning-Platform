package com.elearning.LessonContent;

import com.elearning.entities.LessonContent;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LessonContentRepo extends JpaRepository<LessonContent,Long> {


    @EntityGraph(
            attributePaths = {"lesson","lesson.section" ,"lesson.section.course" , "lesson.section.course.instructor" , "video"},
            type = EntityGraph.EntityGraphType.FETCH
    )
    @Query("select c from LessonContent c where c.id = :lessonId and c.lesson.section.id = :sectionId and c.lesson.section.course.id = :courseId")
    public Optional<LessonContent>findByIdAndLessonAndCourse(Long courseId,Long sectionId,Long lessonId);
}
