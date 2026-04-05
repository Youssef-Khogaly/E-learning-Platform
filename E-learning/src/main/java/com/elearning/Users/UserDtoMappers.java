package com.elearning.Users;

import com.elearning.entities.users.User;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserDtoMappers {


    public UserDto form(User user){
        Objects.requireNonNull(user);
        return UserDto.builder().id(user.getId()).name(user.getName()).roles(user.getRole()).build();
    }
}
