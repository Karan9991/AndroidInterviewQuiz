package com.test.quizexampleinterview.authentication;

import static org.junit.jupiter.api.Assertions.*;

class AuthModelTest {

    AuthModel model;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void getEmail() {
        model = new AuthModel();
        model.setEmail("a");
        assertEquals("a",model.getEmail());
    }

    @org.junit.jupiter.api.Test
    void setEmail() {
    }
}