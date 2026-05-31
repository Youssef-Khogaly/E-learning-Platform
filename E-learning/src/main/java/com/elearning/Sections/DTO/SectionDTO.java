package com.elearning.Sections.DTO;

import lombok.Builder;

@Builder
public record SectionDTO(Long id, Integer index,String title) {
}
