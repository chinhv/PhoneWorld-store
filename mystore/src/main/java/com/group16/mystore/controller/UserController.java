package com.group16.mystore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.group16.mystore.entities.User;
import com.group16.mystore.service.UserService;



import java.util.*;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/user")
public class UserController {
    static String x = null;
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> findAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User findUser(@PathVariable String id){
        return userService.getUserById(Integer.parseInt(id));
    }

    @GetMapping("/current")
    public User findCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByEmail(auth.getName());
    }

    @GetMapping("/")
    public User findUserByEmail(@RequestParam(required = false, value = "email") String email){
        return userService.getUserByEmail(email);
    }

    
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id){
        return userService.deleteUser(Integer.parseInt(id));
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

}
