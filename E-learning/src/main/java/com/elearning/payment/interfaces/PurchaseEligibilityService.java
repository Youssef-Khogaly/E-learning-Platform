package com.elearning.payment.interfaces;

import com.elearning.Courses.Course;
import com.elearning.entities.users.User;

public interface PurchaseEligibilityService {


    boolean canBuy(User user, Course course);
}
