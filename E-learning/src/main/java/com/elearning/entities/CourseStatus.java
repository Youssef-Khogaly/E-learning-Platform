package com.elearning.entities;

import org.apache.commons.lang3.StringEscapeUtils;

public enum CourseStatus {
    DRAFT("DRAFT"),PUBLISHED("PUBLISHED");
    private String str;

    CourseStatus(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return str;
    }
}
