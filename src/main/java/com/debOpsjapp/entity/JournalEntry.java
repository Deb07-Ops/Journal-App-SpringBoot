package com.debOpsjapp.entity;

import com.debOpsjapp.enums.Sentiment;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JournalEntry {
    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;
    public JournalEntry(String title, String content) {
        this.title = title;
        this.content = content;
        this.date = LocalDateTime.now();
    }
    private Sentiment sentiment;

//    public ObjectId getId() { return id; }
//    public void setId(ObjectId id) { this.id = id; }
//    public String getTitle() { return title; }
//    public void setTitle(String title) { this.title = title; }
//    public String getContent() { return content; }
//    public void setContent(String content) { this.content = content; }
//    public Date getDate() { return date; }
//    public void setDate(Date date) { this.date = date; }
}
