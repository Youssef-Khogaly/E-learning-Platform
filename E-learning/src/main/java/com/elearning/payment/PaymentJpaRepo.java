package com.elearning.payment;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface PaymentJpaRepo extends JpaRepository<Payment,Long> {



    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @EntityGraph(
            // to not load course and user entity
            attributePaths = {},
            type = EntityGraph.EntityGraphType.FETCH
    )
    public Optional<Payment> findBySessionId(String sessionId);
}
