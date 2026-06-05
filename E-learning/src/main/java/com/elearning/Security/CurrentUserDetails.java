package com.elearning.Security;


import com.elearning.entities.users.UserRoles;
import lombok.Getter;

import java.util.Objects;

@Getter
public class CurrentUserDetails {

    private final Long id;
    private final UserRoles role;

    public CurrentUserDetails(Long id, UserRoles role) {
        this.id = id;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CurrentUserDetails that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
