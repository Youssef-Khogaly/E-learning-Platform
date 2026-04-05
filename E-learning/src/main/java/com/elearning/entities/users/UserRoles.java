package com.elearning.entities.users;

public enum UserRoles {
    ADMIN("Admin"), Instructor("Instructor") , Student("Student");
    private String str;

    UserRoles(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return str;
    }
}
