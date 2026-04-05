package com.elearning.Users;

import com.elearning.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserJpaRepo extends JpaRepository<User,Long> {

}
