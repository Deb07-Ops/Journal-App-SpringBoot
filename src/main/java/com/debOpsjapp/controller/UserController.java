package com.debOpsjapp.controller;

import com.debOpsjapp.entity.JournalEntry;
import com.debOpsjapp.entity.User;
import com.debOpsjapp.service.JournalEntryService;
import com.debOpsjapp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    private List<User> getAllUsers(){
        return userService.getAll();
    }

    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userInDb= userService.findByUserName(username);
        userInDb.setUsername(user.getUsername());
        userInDb.setPassword(user.getPassword());
        userService.saveNewUser(userInDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
//controller --> service --> repo