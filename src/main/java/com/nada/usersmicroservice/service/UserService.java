package com.nada.usersmicroservice.service;

import com.nada.usersmicroservice.entities.Role;
import com.nada.usersmicroservice.entities.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    User findUserByUsername (String username);
    Role addRole(Role role);
    //User addRoleToUser(String username, String rolename);

    List<User> findAllUsers();

    User ActivateUser (String userame , String code);

    @Query("select r from Role r where r.role_id = ?1")
    Role findRoleById(Long id);


}

