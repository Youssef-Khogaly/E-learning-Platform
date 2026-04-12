package com.elearning.Sections.DTO;

import com.elearning.entities.Section;
import org.springframework.stereotype.Component;

@Component
public class SectionDtoMapper {


    public SectionDTO from(Section section)
    {
        return SectionDTO.builder().id(section.getId()).index(section.getIndex())
                .title(section.getTitle()).build();
    }
}
