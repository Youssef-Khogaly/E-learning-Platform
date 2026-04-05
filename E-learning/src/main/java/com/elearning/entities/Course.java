package com.elearning.entities;

import com.elearning.entities.users.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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
    private CourseStatus status;
}
