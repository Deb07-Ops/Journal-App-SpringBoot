package com.debOpsjapp.service;

import com.debOpsjapp.entity.User;
import com.debOpsjapp.repo.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Mock
    private UserRepo UserRepo;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUsernameTest(){
        // Setup mock
        when(UserRepo.findByUsername(ArgumentMatchers.anyString()))
                .thenReturn(User.builder()
                        .username("ram")
                        .password("inrinrick")
                        .roles(new ArrayList<>())
                        .build());

        // Call method on the injected instance, not the interface
        UserDetails user = userDetailsServiceImpl.loadUserByUsername("ram");

        // Assertions
        Assertions.assertNotNull(user);
        Assertions.assertEquals("ram", user.getUsername());
    }
}
