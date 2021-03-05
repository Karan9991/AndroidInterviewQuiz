package com.test.quizexampleinterview.database;

import androidx.test.InstrumentationRegistry;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import com.test.quizexampleinterview.model.Questions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DbHelperTest {

    private DbHelper dbHelper;
    private List<Questions> queList;
    private Questions questions;

    @Before
    public void setUp() throws Exception {
        dbHelper = new DbHelper(InstrumentationRegistry.getTargetContext());
    }

    @After
    public void tearDown() throws Exception {
        dbHelper = null;
    }

    @Test
    public void getAllQuestions() {
        dbHelper = new DbHelper(InstrumentationRegistry.getTargetContext());
        queList = dbHelper.getAllQuestions();
        questions = queList.get(0);

        assertThat(queList.size(), is(10));
        assertEquals(questions.getQUESTION(),"How to pass the data between activities in Android?");
        assertEquals(questions.getANSWER(),"Intent");
    }

    @Test
    public void rowcount() {
        dbHelper = new DbHelper(InstrumentationRegistry.getTargetContext());
        assertEquals(10, dbHelper.rowcount());
    }
}