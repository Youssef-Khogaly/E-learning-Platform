package com.elearning.Courses;

import com.elearning.entities.users.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Objects;

@Entity
@Getter
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "instructorId")
    @OneToOne
    private User instructor;

    private String title;
    private String desc;
    private Long price;
    private CourseState state;

    protected void setState(CourseState state){
        Objects.requireNonNull(state);
        this.state = state;
    }

}
