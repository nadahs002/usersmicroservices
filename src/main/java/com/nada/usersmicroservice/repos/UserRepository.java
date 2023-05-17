package com.nada.usersmicroservice.repos;

import com.nada.usersmicroservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
