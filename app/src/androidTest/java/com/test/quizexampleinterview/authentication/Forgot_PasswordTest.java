package com.test.quizexampleinterview.authentication;

import android.os.SystemClock;

import androidx.test.rule.ActivityTestRule;

import com.test.quizexampleinterview.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class Forgot_PasswordTest {

    @Rule
    public ActivityTestRule<Forgot_Password> mActivityRule = new ActivityTestRule<>(Forgot_Password.class);


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void view(){
        SystemClock.sleep(1500);

        onView(withId(R.id.editTextforpassemail)).perform(typeText("someString"));

        onView(withId(R.id.buttonsubmit)).perform(click());
    }
}