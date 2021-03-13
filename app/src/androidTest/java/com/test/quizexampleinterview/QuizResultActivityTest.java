package com.test.quizexampleinterview;

import android.os.SystemClock;

import androidx.test.rule.ActivityTestRule;

import com.test.quizexampleinterview.authentication.SignIn;

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

public class QuizResultActivityTest {

    @Rule
    public ActivityTestRule<QuizResultActivity> mActivityRule = new ActivityTestRule<>(QuizResultActivity.class);


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void view(){
        SystemClock.sleep(1500);

        onView(withText("Result")).check(matches(isDisplayed()));
        onView(withText("Greetings")).check(matches(isDisplayed()));
        onView(withText("Score")).check(matches(isDisplayed()));

        onView(withId(R.id.playagainbtn)).perform(click());

    }

    @Test
    public void testCheckDialogDisplayed() {
        // Click on the button that shows the dialog
        onView(withId(R.id.logout)).perform(click());

        // Check the dialog title and message text is displayed
        onView(withText("SIGN OUT")).check(matches(isDisplayed()));
        onView(withText("Are you sure you would like to sign out?")).check(matches(isDisplayed()));

    }

    @Test
    public void testClickOkButton() {
        onView(withId(R.id.logout)).perform(click());

        // android.R.id.button1 = positive button
        onView(withId(android.R.id.button1)).perform(click());
    }

    @Test
    public void testClickCancelButton() {
        onView(withId(R.id.logout)).perform(click());

        // android.R.id.button1 = positive button
        onView(withId(android.R.id.button2)).perform(click());
    }

    @Test
    public void singOutTest(){
        onView(withId(R.id.logout)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        SystemClock.sleep(3000);
    }
}