package com.test.quizexampleinterview.authentication;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Forgot_PasswordTest implements AuthContract.View{

    private Forgot_Password forgot_password;
    private AuthModel authModel;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Override
    public void initUI() {

    }

    @Override
    public String getEmail() {
        return authModel.getEmail();
    }

    @Test
    public void getEail() {
        authModel = new AuthModel();
        forgot_password = new Forgot_Password();
       // authModel.setEmail("f");
        assertEquals(null, getEmail());
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public void initAnimateButton() {

    }

    @Override
    public void animateButton() {

    }

    @Override
    public void checkFirebaseLogin() {

    }
}