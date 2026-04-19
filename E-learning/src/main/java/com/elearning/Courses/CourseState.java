package com.elearning.Courses;

public enum CourseState {
    DRAFT("DRAFT"),PUBLISHED("PUBLISHED"),UNPUBLISHED("UNPUBLISHED")  ;
    private String str;

    CourseState(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return str;
    }
}
