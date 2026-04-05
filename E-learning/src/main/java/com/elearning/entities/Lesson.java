package com.elearning.entities;

import com.elearning.entities.video.Video;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    private String text;


    @OneToOne
    @JoinColumn(name = "videoId")
    private Video video;
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
