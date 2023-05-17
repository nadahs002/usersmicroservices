package com.nada.usersmicroservice.repos;

import com.nada.usersmicroservice.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}

