package com.nada.usersmicroservice.repos;

import com.nada.usersmicroservice.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {


    @Query("select r from Role r where r.role_id = ?1")
    Role findRoleById(Long id);
}

