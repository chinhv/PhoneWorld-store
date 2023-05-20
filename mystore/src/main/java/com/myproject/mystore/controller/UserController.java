package com.myproject.mystore.controller;

import org.springframework.beans.factory.annotation.Autowired;




import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.mystore.entities.User;
import com.myproject.mystore.security.JwtUtils;
import com.myproject.mystore.service.UserService;

import java.util.*;


import javax.servlet.http.HttpServletRequest;


@CrossOrigin(origins = "http://127.0.0.1:5500", allowCredentials = "true")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private JwtUtils jwtUtils;

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
    public User getCurrentUser(HttpServletRequest request){
        String jwt = jwtUtils.getJwtFromCookie(request);
        return userService.getUserByEmail(jwtUtils.getEmailFromJwtToken(jwt));
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
