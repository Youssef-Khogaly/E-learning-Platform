package com.elearning.entities;

import com.elearning.Courses.Course;
import com.elearning.entities.users.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
public class UserEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "usrId")
    private User user;
    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;

    private Instant enrollDate;

}
