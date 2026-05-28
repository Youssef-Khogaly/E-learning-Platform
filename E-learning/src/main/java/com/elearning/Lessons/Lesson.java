package com.elearning.Lessons;

import com.elearning.entities.Section;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer index;
    @JoinColumn(name = "sectionId",nullable = false)
    @ManyToOne(optional = false)
    private Section section;
    @Enumerated(EnumType.STRING)
    private LessonType type;
    private String title;

    private LessonState state;
    private Instant lastPublishedAt;
    private Instant lastUnpublishedAt;

    private Boolean isPreview;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Lesson lesson)) return false;
        return Objects.equals(getId(), lesson.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
