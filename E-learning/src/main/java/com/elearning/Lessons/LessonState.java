package com.elearning.Lessons;

public enum LessonState {
    DRAFT("DRAFT"),PUBLISHED("PUBLISHED"),UNPUBLISHED("UNPUBLISHED");
    private String str;

    LessonState(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return str;
    }
}
