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
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class SignInTest {

    @Rule
    public ActivityTestRule<SignIn> mActivityRule = new ActivityTestRule<>(SignIn.class);


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void view(){
        SystemClock.sleep(1500);


        onView(withText("Don't have an account?")).check(matches(isDisplayed()));
        onView(withText("Sign Up")).check(matches(isDisplayed()));
        onView(withText("Forgot Password?")).check(matches(isDisplayed()));


        onView(withId(R.id.editTextsignemail)).perform(typeText("someString"));
        onView(withId(R.id.editTextsignpass)).perform(typeText("someString"));

        onView(withId(R.id.button_login)).perform(click());
        onView(withId(R.id.tvforgotpass)).perform(click());
    }
}