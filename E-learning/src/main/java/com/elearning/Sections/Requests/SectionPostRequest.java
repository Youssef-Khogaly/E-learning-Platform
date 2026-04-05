package com.elearning.Sections.Requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record SectionPostRequest(@PositiveOrZero Integer index , @NotNull @NotBlank String title) {
}
