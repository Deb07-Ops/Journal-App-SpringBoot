package com.debOpsjapp.service;

import com.debOpsjapp.entity.User;
import com.debOpsjapp.repo.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class UserService {

    @Autowired
    private final UserRepo userRepo;
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    public void saveEntry(User user){
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
    public User findByUsername(String username){
        return userRepo.findByUsername(username);
    }
}
//controller --> service --> repo