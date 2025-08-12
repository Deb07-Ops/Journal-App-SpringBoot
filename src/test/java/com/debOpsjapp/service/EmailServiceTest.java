package com.debOpsjapp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;
    @Test
    public void sendEmailTest(){
        emailService.sendEmail("dymtry.mail02@gmail.com",
                "Hi , this is an SpringBoot email service test ",
                "Hi, email testing phase 1");
    }
}
