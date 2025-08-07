package com.debOpsjapp.controller;

import com.debOpsjapp.entity.User;
import com.debOpsjapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @GetMapping("/health-check")
    public String healthCheck() {
        return "OK";
    }

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user){
        try {
            //Check if user already exists
            if (userService.findByUserName(user.getUsername()) != null) {
                return new ResponseEntity<>("Username already exists!", HttpStatus.CONFLICT);
            }

            //Set default role if none provided
            if (user.getRoles() == null || user.getRoles().isEmpty()) {
                user.getRoles().add("USER");
            }
            userService.saveNewUser(user);
            return new ResponseEntity<>("User registered successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Registration failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
