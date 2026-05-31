package com.elearning.Courses;

import com.elearning.entities.users.User;
import com.elearning.util.Money;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "instructor_id",nullable = false)
    @OneToOne(optional = false)
    private User instructor;

    private String title;
    @Column(name = "`desc`")
    private String desc;
    @Embedded
    private Money price;
    @Enumerated(EnumType.STRING)
    private CourseState state;
    @Column(name = "publishedAt")
    private Instant publishedAt;
    @Column(name = "unPublishedAt")
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
