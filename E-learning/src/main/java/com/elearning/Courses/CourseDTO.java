package com.elearning.Courses;

import com.elearning.Users.UserDto;
import com.elearning.util.Money;
import lombok.Builder;

import java.time.Instant;

@Builder
public record CourseDTO(Long id , String title , String desc , CourseState status, Money price , UserDto instructor , Instant publishedAt) {
}
