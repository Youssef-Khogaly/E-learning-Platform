package com.elearning.Lessons;

import com.elearning.entities.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonJpaRepo extends JpaRepository<Lesson,Integer> {
}
