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
import static androidx.test.espresso.matcher.ViewMatchers.isFocusable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

//import android.test.ActivityInstrumentationTestCase2;

public class SignUpTest {

    @Rule
    public ActivityTestRule<SignUp> mActivityRule = new ActivityTestRule<>(SignUp.class);


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void view(){
        SystemClock.sleep(1500);

        onView(withText("Already have an account?")).check(matches(isDisplayed()));
        onView(withText("SignIn")).check(matches(isDisplayed()));

        onView(withId(R.id.editTextsignemail)).perform(typeText("someString"));
        onView(withId(R.id.editTextsignpass)).perform(typeText("someString"));
        onView(withId(R.id.editTextsigncnfrmpass)).perform(typeText("someString"));

        onView(withId(R.id.buttonsignup)).perform(click());

    }

    @Test
    public void edittext(){
        SystemClock.sleep(1500);

        onView(withId(R.id.editTextsignemail)).check(matches(isFocusable()));
        SystemClock.sleep(1500);

        onView(withId(R.id.editTextsignpass)).check(matches(isFocusable()));
        SystemClock.sleep(1500);

        onView(withId(R.id.editTextsigncnfrmpass)).check(matches(isFocusable()));

    }
}