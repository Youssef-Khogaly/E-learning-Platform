package com.elearning.Sections;

import com.elearning.entities.Section;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SectionJpaRepo extends JpaRepository<Section,Long> {



    List<Section> findAllByCourse_Id(Long courseId);


    @Query("""
        select case when count (l) = 0 then true
                else false end
                from Lesson l where l.section.id = :sectionId
       """)
    boolean isEmptySection(Long sectionId);
    @EntityGraph(
            attributePaths = {"lessonSet"},
            type = EntityGraph.EntityGraphType.FETCH
    )
    @Query("select s from Section s where  s.id = s.id")
    Optional<Section>findByIdWithLessons(Long secitonId);

    boolean existsByCourse_IdAndIndex(Long courseId, Integer index);

    boolean existsByIdAndCourse_Id(Long id, Long courseId);
}
