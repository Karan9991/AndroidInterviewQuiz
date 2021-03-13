package com.test.quizexampleinterview.authentication;

import android.content.Intent;
import android.os.SystemClock;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.NoActivityResumedException;
import androidx.test.rule.ActivityTestRule;

import com.test.quizexampleinterview.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isFocusable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.fail;

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

    @Test
    public void edittext(){
        SystemClock.sleep(1500);

        onView(withId(R.id.editTextsignemail)).check(matches(isFocusable()));
        SystemClock.sleep(1500);

        onView(withId(R.id.editTextsignpass)).check(matches(isFocusable()));
    }

    @Test
    public void exitAppTest(){
        assertPressingBackExitsApp();
    }

    private void assertPressingBackExitsApp() {
        try {
            pressBack();
            fail("Should kill the app and throw an exception");
        } catch (NoActivityResumedException e) {
            // Test Passed
        }
    }

    @Test
    public void perform_SignIn_Test(){
        onView(withId(R.id.editTextsignemail)).perform(typeText("000111karan@gmail.com"));
        onView(withId(R.id.editTextsignpass)).perform(typeText("Karan6$"));
        SystemClock.sleep(2000);

        onView(withId(R.id.button_login)).perform(click());
        SystemClock.sleep(2000);
    }

    @Test
    public void launchActivityTest(){
        launchNewTaskActivity();
    }

    private void launchNewTaskActivity() {
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(),
                SignIn.class);
        mActivityRule.launchActivity(intent);
    }
}