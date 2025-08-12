package com.debOpsjapp.scheduler;

import com.debOpsjapp.entity.JournalEntry;
import com.debOpsjapp.entity.User;
import com.debOpsjapp.enums.Sentiment;
import com.debOpsjapp.repo.UserRepoImpl;
import com.debOpsjapp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepoImpl userRepo;

    public void fetUserandSM(){
        List<User> users=userRepo.getUserforSentimentAnalysis();

        for(User user:users){
            List<JournalEntry> journalEntries= user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now()
                    .minus(7, ChronoUnit.DAYS)))
                    .map(x -> x.getSentiment()).collect(Collectors.toList());
            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();
            for (Sentiment sentiment : sentiments) {
                if (sentiment != null)
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
            }
            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;
            for (Map.Entry<Sentiment, Integer> entry : sentimentCounts.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }
            if (mostFrequentSentiment != null) {
                    emailService.sendEmail(user.getEmail(), "Sentiment for the last 7 days ", mostFrequentSentiment.toString());
                }
        }
    }
}
