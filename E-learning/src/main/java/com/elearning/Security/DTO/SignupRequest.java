package com.elearning.Security.DTO;

import com.elearning.entities.users.UserRoles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.Check;
import org.hibernate.validator.constraints.Length;

public record SignupRequest(@NotBlank @Length(min = 3,max = 32, message = "name length must be between 8 and 32")String name,
                            @NotBlank @Length(min = 8,max = 255 , message = "invalid email")
                            @Email
                            String email,
                            @NotBlank @Length(min = 8 , max = 64)
                            @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$") String password,
                            @NotNull UserRoles role) {
}
