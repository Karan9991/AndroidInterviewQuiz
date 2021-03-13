package com.test.quizexampleinterview;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;


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