package com.elearning.LessonContent;

import com.elearning.entities.LessonContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LessonContentRepo extends JpaRepository<LessonContent,Long> {


    @Query("select c from LessonContent c inner join Lesson l on l.id = :lessonId inner join Section s on s.id = :sectionId inner join Course co on co.id = :courseId")
    public Optional<LessonContent>findByIdAndLessonAndCourse(Long courseId,Long sectionId,Long lessonId);
}
