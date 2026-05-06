package com.elearning.Lessons;

public enum LessonStatus {
    DRAFT("DRAFT"),PUBLISHED("PUBLISHED"),UNPUBLISHED("UNPUBLISHED");
    private String str;

    LessonStatus(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return str;
    }
}
