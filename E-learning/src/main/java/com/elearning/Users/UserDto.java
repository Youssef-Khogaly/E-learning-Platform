package com.elearning.Users;

import com.elearning.entities.users.UserRoles;
import lombok.Builder;

@Builder
public record UserDto(Long id , String name , UserRoles roles) {
}
