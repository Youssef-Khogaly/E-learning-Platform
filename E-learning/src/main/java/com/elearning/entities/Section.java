package com.elearning.entities;

import com.elearning.Courses.Course;
import com.elearning.Lessons.Lesson;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "section")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer index;
    @JoinColumn(name = "courseId",nullable = false)
    @ManyToOne(optional = false)
    private Course course;
    private String title;

    @OneToMany(mappedBy = "section")
    private Set<Lesson> lessonSet = new HashSet<>();




}
