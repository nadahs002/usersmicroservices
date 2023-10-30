package com.nada.usersmicroservice.restcontrollers;

import com.nada.usersmicroservice.entities.Role;
import com.nada.usersmicroservice.entities.User;
import com.nada.usersmicroservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UserRestController {
    @Autowired
    UserService userService;
    @RequestMapping(path = "all", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    /*@RequestMapping(path = "/addUser", method = RequestMethod.POST){

    }*/

    @RequestMapping(path="role/{id}",method=RequestMethod.GET)
    public Role findRoleById(@PathVariable Long id) {
        return userService.findRoleById(id);
    }
}
