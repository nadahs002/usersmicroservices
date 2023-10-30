package com.nada.usersmicroservice.service;

import com.nada.usersmicroservice.entities.Role;
import com.nada.usersmicroservice.entities.User;
import com.nada.usersmicroservice.repos.RoleRepository;
import com.nada.usersmicroservice.repos.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Transactional
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRep;
    @Autowired
    RoleRepository roleRep;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${spring.mail.username}")
    private String  from;
    @Autowired
    private JavaMailSender mailSender;
    @Override
    public User saveUser(User user) {
        Random rand = new Random();
        String code = String.format("%04d", rand.nextInt(10000));



        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(from);
        msg.setTo(user.getEmail());
        msg.setSubject("your code of the authentication is :");
        String activationURL = "http://localhost:8082/users/activateUser/" + user.getUsername() + "/" + code;

        msg.setText(     "Dear " + user.getUsername() + "," +
                "\n\n" +
                "Welcome to our world of series . To activate your account, please click on the following link: " +
                "\n\n" + activationURL +
                "\n\n");
        //msg.setText(code);
        mailSender.send(msg);
        List<Role> listOfrole=new ArrayList<>();
        listOfrole.add(roleRep.findRoleById(2L));
        user.setRoles(listOfrole);

        user.setEmail(user.getEmail());
        user.setCode(code);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRep.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRep.findAll();
    }

    @Override
    public User ActivateUser(String userame, String code) {
        User user= userRep.findByUsername(userame);
        if (user !=null){
            if (user.getEnabled()== null || user.getEnabled()==false){
                if (user.getCode().equals(code)== true){
                    user.setEnabled(true);
                    userRep.save(user);
                    return user;
                }
                else {
                    System.out.println(user.getCode());
                    return null;
                }
            }else{
                return null ;
            }
        }
        else{
            return null;
        }
    }

    @Override
    public Role findRoleById(Long id) {
        return roleRep.findRoleById(id);
    }

    @Override
    public Role addRole(Role role) {
        return roleRep.save(role);
    }

   /* @Override
    public User addRoleToUser(String username, String rolename) {
        return null;
    }*/

    @Override
    public User findUserByUsername(String username) {
        return userRep.findByUsername(username);
    }
}
