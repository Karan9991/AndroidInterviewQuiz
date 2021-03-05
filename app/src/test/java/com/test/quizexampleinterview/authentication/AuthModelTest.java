package com.test.quizexampleinterview.authentication;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

import static org.junit.jupiter.api.Assertions.*;

class AuthModelTest {

    AuthModel model;

    @Captor
    private ArgumentCaptor<String> mString;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        model = new AuthModel("000karan@gmail.com","password");

    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        model = null;
    }

    @org.junit.jupiter.api.Test
    void getEmail() {
        model = new AuthModel("000karan@gmail.com","password");
        assertEquals("000karan@gmail.com",model.getEmail());
        assertEquals("password",model.getPassword());
    }

}