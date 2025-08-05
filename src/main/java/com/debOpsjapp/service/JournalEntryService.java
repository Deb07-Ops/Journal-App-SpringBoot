package com.debOpsjapp.service;

import com.debOpsjapp.entity.JournalEntry;
import com.debOpsjapp.entity.User;
import com.debOpsjapp.repo.JournalEntryRepo;
import org.bson.types.ObjectId;
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

    public JournalEntryService(JournalEntryRepo journalEntryRepo) {
        this.journalEntryRepo = journalEntryRepo;
    }

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        User user = userService.findByUsername(userName); //first find the user
        JournalEntry saved = journalEntryRepo.save(journalEntry);
        user.getJournalEntries().add(saved);
        user.setUsername(null);
        userService.saveEntry(user);
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
    public void deleteByID(ObjectId id, String userName){
        User user = UserService.findByUsername(userName); //first find the user
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepo.deleteById(id);
    }
}
//controller --> service --> repo