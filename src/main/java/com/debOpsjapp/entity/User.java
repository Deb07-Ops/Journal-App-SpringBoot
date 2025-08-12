package com.debOpsjapp.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Document(collection = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    @NonNull
    private String username;
    private String email;
    private boolean sentimentAnalysis;
    @NonNull
    private String password;
    private List<String> roles  = new ArrayList<>();
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.roles = new ArrayList<>(Arrays.asList("USER"));
        this.journalEntries = new ArrayList<>();
    }
    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();
}
