package com.elearning.Videos;

public enum EnVideoSortBy {
    create("createdAt") , updated("updatedAt");
    private String str;

    EnVideoSortBy(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return str;
    }
}
