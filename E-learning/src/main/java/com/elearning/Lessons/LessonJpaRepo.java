package com.elearning.Lessons;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonJpaRepo extends JpaRepository<Lesson,Integer> {
}
