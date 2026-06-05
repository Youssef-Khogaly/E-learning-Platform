package com.elearning.Lessons;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum LessonType {
    TXT("TXT"),VIDEO("VIDEO");
    private String str;

    LessonType(String str) {
        this.str = str;
    }

    @JsonValue
    public String getStr() {
        return str;
    }

    @JsonCreator
    public static LessonType from(String value) {
        for (LessonType t : values()) {
            if (t.str.equalsIgnoreCase(value)) {
                return t;
            }
        }
        throw new IllegalArgumentException("Invalid LessonType: " + value);
    }

    @Override
    public String toString() {
        return str;
    }
}
