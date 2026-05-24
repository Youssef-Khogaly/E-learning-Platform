package com.elearning.payment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentJpaRepo extends JpaRepository<Payment,Long> {
}
