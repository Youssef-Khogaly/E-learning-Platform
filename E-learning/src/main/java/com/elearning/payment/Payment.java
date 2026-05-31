package com.elearning.payment;

import com.elearning.Courses.Course;
import com.elearning.entities.users.User;
import com.elearning.util.Money;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "payments")
public class Payment{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @OneToOne(optional = false)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Embedded
    private Money totalAmount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod method;
    private PaymentStatus status;

    @OneToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private String transaction_id;
    @Column(unique = true)
    private String sessionId;
    private long paidAt;

    @CreationTimestamp
    private Instant createdAt;

}
