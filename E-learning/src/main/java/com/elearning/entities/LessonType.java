package com.elearning.entities;

public enum LessonType {
    TXT("TXT"),VIDEO("VIDEO");
    private String str;

    LessonType(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return str;
    }
}
