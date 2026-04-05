package com.elearning.Sections;

import com.elearning.entities.Section;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionJpaRepo extends JpaRepository<Section,Integer> {
}
