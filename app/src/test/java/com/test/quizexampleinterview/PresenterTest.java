package com.test.quizexampleinterview;

import android.content.Context;

import com.test.quizexampleinterview.authentication.SignIn;
import com.test.quizexampleinterview.authentication.SignUp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

import static org.junit.Assert.*;


public class PresenterTest {

    @Mock
    private Presenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void logout() {
        presenter.logout();
        verify(presenter).logout();
    }
}