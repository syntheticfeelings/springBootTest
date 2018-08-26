package com.syntheticfeelingss.repos;

import com.syntheticfeelingss.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
