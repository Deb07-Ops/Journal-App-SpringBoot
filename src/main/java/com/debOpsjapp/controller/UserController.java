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

    @PostMapping
    public void crteateUser(@RequestBody User user){
        userService.saveEntry(user);
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String username){
        User userInDb= userService.findByUsername(username);
        if(userInDb !=null){
            userInDb.setPassword(user.getPassword());
            userInDb.setUsername(user.getUsername());
            userService.saveEntry(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
//controller --> service --> repo