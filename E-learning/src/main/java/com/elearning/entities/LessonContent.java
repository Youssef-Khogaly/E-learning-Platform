package com.elearning.entities;

import com.elearning.Lessons.Lesson;
import com.elearning.entities.video.Video;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
public class LessonContent {

    @Id
    private Long id;
    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "id",nullable = false)
    private Lesson lesson;
    private String text;
    private int duration;
    @OneToOne(optional = true)
    @JoinColumn(name = "videoId",nullable = true)
    private Video video;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof LessonContent that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
