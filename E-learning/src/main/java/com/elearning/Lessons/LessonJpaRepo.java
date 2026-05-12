package com.elearning.Lessons;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface LessonJpaRepo extends JpaRepository<Lesson,Long> {

    @Query("select l from Lesson l where l.section.id = :sectionId and l.section.course.id = :courseId")
    List<Lesson>findAllByCourseAndSection(Long courseId , Long sectionId);

    Optional<Lesson> findByIdAndSection_Id(Integer id, Long sectionId);

    @Query("select l from Lesson l inner join Section s on l.id =: lessonId and l.section.id = s.id inner join Course c on c.id = s.id")
    Optional<Lesson>findByWithSectionAndCourse(Long courseId,Long sectionId,Long lessonId);
}
