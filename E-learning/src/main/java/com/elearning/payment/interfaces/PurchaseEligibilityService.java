package com.elearning.payment.interfaces;

import com.elearning.Courses.Course;
import com.elearning.Security.CurrentUserDetails;
import com.elearning.entities.users.User;

public interface PurchaseEligibilityService {


    boolean canBuy(CurrentUserDetails user, Course course);
}
