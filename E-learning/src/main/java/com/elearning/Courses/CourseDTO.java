package com.elearning.Courses;

import com.elearning.Users.UserDto;
import com.elearning.entities.CourseStatus;
import lombok.Builder;

@Builder
public record CourseDTO(Long id , String title , String desc , CourseStatus status, long price , UserDto instructor) {
}
