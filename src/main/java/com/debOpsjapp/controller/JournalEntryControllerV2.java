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

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService UserService;
    @GetMapping("{userName}") //we get here if we do GET request
    public ResponseEntity<?> getAllJournalEntriesofUser(@PathVariable String userName){//Methods inside a controller class should be public sho that they can
        // be accessed and invoked by the spring framework or external HTTP Request
        User user = UserService.findByUsername(userName); // Get user by the username
        List<JournalEntry> all = user.getJournalEntries();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{userName}") //we go here if we do POST request
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String userName){ //It's like asking the spring to take data from the req and turn into java obj that can be used in my cod
        try {

            myEntry.setDate(new Date());
            journalEntryService.saveEntry(myEntry,userName);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getEntryId(@PathVariable ObjectId myId){
        Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
        return journalEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @DeleteMapping("id/{userName}/{myId}")
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId myId, @PathVariable String userName){
        journalEntryService.deleteByID(myId , userName);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
//    @PutMapping("id/{id}")
//    public JournalEntry updateEntry(@PathVariable ObjectId id , @RequestBody JournalEntry newEntry){
//        JournalEntry old = journalEntryService.findById(id).orElse(null);
//        if(old != null){
//            old.setDate(myEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle():old.getTitle());
//            old.setContent(newEntry.getContent() != null && newEntry.getContent().equals("") ? newEntry.getContent():old.getContent());
//        }
//        journalEntryService.saveEntry(old);
//       return null;
//    }
@PutMapping("id/{userName}/{id}")
public ResponseEntity<?> updateJournalbyID(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry, @PathVariable String userName){
    JournalEntry old = journalEntryService.findById(id).orElse(null);
    if (old != null) {
        // Update title if new non-empty title provided
        if (newEntry.getTitle() != null && !newEntry.getTitle().isEmpty()) {
            old.setTitle(newEntry.getTitle());
        }
        // Update content if new non-empty content provided
        if (newEntry.getContent() != null && !newEntry.getContent().isEmpty()) {
            old.setContent(newEntry.getContent());
        }
        // Optionally, update date to now
        old.setDate(new Date());
        journalEntryService.saveEntry(old, userName);
        return new ResponseEntity<>(old,HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}

}
//controller --> service --> repo