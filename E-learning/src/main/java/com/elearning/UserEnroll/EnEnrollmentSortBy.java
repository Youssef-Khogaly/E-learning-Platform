package com.elearning.UserEnroll;

import com.elearning.Courses.EnCourseSortBy;

public enum EnEnrollmentSortBy {
    EnrollDate("enrollDate"), TITLE("title");
    private String str;

    EnEnrollmentSortBy(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return str;
    }
}
