package com.elearning.payment.interfaces;

import com.elearning.Courses.Course;
import com.elearning.entities.users.User;
import com.elearning.payment.PaymentMethod;
import com.elearning.payment.dto.PaymentSessionDto;

public interface PaymentSessionService {


    PaymentSessionDto createPaymentSession(User user, Course course, PaymentMethod method);
}
