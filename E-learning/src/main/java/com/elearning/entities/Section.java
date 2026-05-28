package com.elearning.entities;

import com.elearning.Courses.Course;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "sections")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer index;
    @JoinColumn(name = "courseId",nullable = false)
    @ManyToOne(optional = false)
    private Course course;
    private String title;




}
