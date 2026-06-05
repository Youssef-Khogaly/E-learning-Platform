package com.elearning.entities.users;

import org.springframework.security.core.GrantedAuthority;

public enum UserRoles {
    ADMIN("Admin"), Instructor("Instructor") , Student("Student");
    private String str;

    UserRoles(String str) {
        this.str = str;
    }
    public static UserRoles from(GrantedAuthority grantedAuthority)
    {
        String role = grantedAuthority.getAuthority();
        if(role.equals("ROLE_"+ ADMIN))
            return ADMIN;
        if(role.equals("ROLE_"+ Instructor))
            return Instructor;
        if(role.equals("ROLE_"+ Student))
            return Student;
        throw new RuntimeException("Invalid user role!! cannot map from granted authority to role");
    }
    @Override
    public String toString() {
        return str;
    }
}
