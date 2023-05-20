package com.myproject.mystore.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.mystore.dto.LogInDto;
import com.myproject.mystore.dto.SignUpDto;
import com.myproject.mystore.repository.UserRepository;
import com.myproject.mystore.security.JwtUtils;
import com.myproject.mystore.service.CustomUserDetails;
import com.myproject.mystore.service.UserService;

@CrossOrigin(origins = "http://127.0.0.1:5500", allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/log-in")
    public ResponseEntity<?> authenticateUser(@RequestBody LogInDto dto){
        String cookie = null;
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dto.getEmail(), dto.getPassword()
            ));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
            cookie = jwtCookie.toString();
        } catch (Exception e) {
            return new ResponseEntity<>("Wrong password or email", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie).body("Log-in successfully!");
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody SignUpDto dto){
        if(userRepository.existsByEmail(dto.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        userService.saveUser(dto);
        return new ResponseEntity<>("Register successfully !", HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logoutUser(){
        ResponseCookie cookie = jwtUtils.cleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body("You've been signed out!");
    }
}
