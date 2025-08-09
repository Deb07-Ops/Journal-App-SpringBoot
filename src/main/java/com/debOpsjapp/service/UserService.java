package com.debOpsjapp.service;

import com.debOpsjapp.entity.User;
import com.debOpsjapp.repo.UserRepo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Component
public class UserService {

    @Autowired
    private final UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    public void saveNewUser(User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepo.save(user);
        } catch (Exception e) {
            logger.error("User save failed", user.getUsername(),e);
        }
    }

    public void saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepo.save(user);
    }

    public void saveUser(User user){

        userRepo.save(user);
    }

    public List<User> getAll(){

        return userRepo.findAll();
    }

    public Optional<User> findById(ObjectId id){

        return userRepo.findById(id);
    }

    public void deleteById(ObjectId id){

        userRepo.deleteById(id);
    }

    public User findByUserName(String username){

        return userRepo.findByUsername(username);
    }
}
//controller --> service --> repo