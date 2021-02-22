package com.test.quizexampleinterview.authentication;

import android.app.Activity;
import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

public class AuthPresenterTest {

    @Mock
    AuthContract.View view;

    @Mock
    Activity activity;

    @Mock
    Context context;

    private AuthPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void setPresenter(){

        presenter = new AuthPresenter(view, activity, context);
       // verify(view).setPresenter(presenter);
        verify(view).initUI();


    }
}