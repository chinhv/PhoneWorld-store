package com.group16.mystore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

import com.group16.mystore.dto.SignUpDto;
import com.group16.mystore.entities.Role;
import com.group16.mystore.entities.User;
import com.group16.mystore.repository.RoleRepository;
import com.group16.mystore.repository.UserRepository;

import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(int id){
        return userRepo.findById(id).orElse(null);
    }

    public User getUserByName(String userName){
        return userRepo.findByUserName(userName);
    }

    public User getUserByEmail(String email){
        return userRepo.findByEmail(email);
    }

    public User saveUser(SignUpDto dto){
        User user = new User();
        user.setUserName(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setPhone(dto.getPhone());
        Role roles = new Role();
        if(dto.getEmail().equals("admin@gmail.com")){
            roles = roleRepository.findByRoleName("ADMIN");
        }else{
            roles = roleRepository.findByRoleName("USER");
        }

        user.setRoles(Collections.singleton(roles));
        return userRepo.save(user);
    }

    public String deleteUser(int id){
        userRepo.deleteById(id);
        return "delete done !";
    }

    public User updateUser(User user){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepo.findByEmail(auth.getName());
        currentUser.setUserName(user.getUserName());
        currentUser.setEmail(user.getEmail());
        currentUser.setPhone(user.getPhone());
        return userRepo.save(currentUser);
    }

}
