package com.debOpsjapp.service;

import com.debOpsjapp.entity.JournalEntry;
import com.debOpsjapp.entity.User;
import com.debOpsjapp.repo.JournalEntryRepo;
import com.debOpsjapp.repo.UserRepo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Component
public class JournalEntryService {

    @Autowired
    private final JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserService UserService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;



    public JournalEntryService(JournalEntryRepo journalEntryRepo) {
        this.journalEntryRepo = journalEntryRepo;
    }

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        User user = userService.findByUserName(userName); //first find the user
        JournalEntry saved = journalEntryRepo.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveUser(user);
    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepo.save(journalEntry);
    }
    public List<JournalEntry> getAll(){
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId myId){
        return journalEntryRepo.findById(myId);
    }
    public boolean deleteByID(ObjectId id, String userName){
        boolean boo = false;
        try {
            User user = UserService.findByUserName(userName); //first find the user
            boo = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (boo) {
                userService.saveUser(user);
                journalEntryRepo.deleteById(id);
            }
        }catch(Exception e){
            System.out.println(e);
            throw new RuntimeException("Error occurred while detecting entry.", e);
        }
        return boo;
    }

}
//controller --> service --> repo