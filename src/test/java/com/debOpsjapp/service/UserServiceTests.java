package com.debOpsjapp.service;

import com.debOpsjapp.repo.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepo userRepo;
    

    @Test
    public void testFinbByUsername(){
        assertEquals(4,2+2);
        assertNotNull(userRepo.findByUsername("ram"));
    }
}
