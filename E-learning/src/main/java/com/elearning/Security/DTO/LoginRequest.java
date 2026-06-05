package com.elearning.Security.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

public record LoginRequest(@NotBlank @Length(min = 8,max = 255) String email
        , @NotBlank @Length(min = 8 , max = 64) String password) {
}
