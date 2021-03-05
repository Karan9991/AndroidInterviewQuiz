package com.test.quizexampleinterview.authentication;

import android.app.Activity;
import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.test.quizexampleinterview.Validate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuthPresenterTest {

    @Mock
    AuthPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void setPresenter(){
        presenter.Login();
        verify(presenter).Login();
        presenter.SignUp();
        verify(presenter).SignUp();
        presenter.ForgotPassword();
        verify(presenter).ForgotPassword();
        presenter.checkFirebaseLogin();
        verify(presenter).checkFirebaseLogin();
    }
}