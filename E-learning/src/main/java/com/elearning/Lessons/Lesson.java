package com.elearning.Lessons;

import com.elearning.entities.Section;
import com.elearning.entities.video.Video;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer index;
    @JoinColumn(name = "sectionId",nullable = false)
    @ManyToOne(optional = false)
    private Section section;
    @Enumerated(EnumType.STRING)
    private LessonType type;
    private String title;

    private LessonStatus status;
    private Instant last_published_timeStamp;
    private Instant last_unpublished_timeStamp;

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
