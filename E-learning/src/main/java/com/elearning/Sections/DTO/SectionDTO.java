package com.elearning.Sections.DTO;

import lombok.Builder;

@Builder
public record SectionDTO(Integer id, Integer index,String title) {
}
