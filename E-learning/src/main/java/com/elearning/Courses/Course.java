package com.elearning.Courses;

import com.elearning.entities.users.User;
import com.elearning.util.Money;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;
import java.util.Objects;

@Entity
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
    private Money price;
    private CourseState state;
    private Instant publishedAt;
    private Instant unPublishedAt;

    public Long getId() {
        return id;
    }

    public User getInstructor() {
        return instructor;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public Money getPrice() {
        return new Money(this.price);
    }

    public CourseState getState() {
        return state;
    }

    public Instant getPublishedAt() {
        return publishedAt;
    }

    public Instant getUnPublishedAt() {
        return unPublishedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setInstructor(User instructor) {
        this.instructor = instructor;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    protected void setPublishedAt(Instant lastPublishTimeStamp) {
        this.publishedAt = lastPublishTimeStamp;
    }

    protected void setUnPublishedAt(Instant lastUnPublishTimeStamp) {
        this.unPublishedAt = lastUnPublishTimeStamp;
    }

    protected void setState(CourseState state){
        Objects.requireNonNull(state);
        this.state = state;
    }

}
