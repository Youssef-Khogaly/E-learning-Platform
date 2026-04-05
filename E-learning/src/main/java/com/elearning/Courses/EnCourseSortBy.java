package com.elearning.Courses;

public enum EnCourseSortBy {
    ID("id") , PRICE("price") , TITLE("title");
    private String str;

    EnCourseSortBy(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return str;
    }
}
