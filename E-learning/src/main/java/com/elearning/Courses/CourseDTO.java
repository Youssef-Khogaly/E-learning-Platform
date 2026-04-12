package com.elearning.Courses;

import com.elearning.Users.UserDto;
import lombok.Builder;

@Builder
public record CourseDTO(Long id , String title , String desc , CourseState status, long price , UserDto instructor) {
}
