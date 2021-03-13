package com.test.quizexampleinterview;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class QuizMainActivityTest {

    @Rule
    public ActivityTestRule<QuizMainActivity> mActivityRule = new ActivityTestRule<>(QuizMainActivity.class);

    @Test
    public void radioButtonClick(){
        onView(withId(R.id.rbOption1)).perform(click());

        onView(withId(R.id.rbOption1)).check(matches(isChecked()));
        onView(withId(R.id.rbOption2)).check(matches(isNotChecked()));
        onView(withId(R.id.rbOption3)).check(matches(isNotChecked()));
        onView(withId(R.id.rbOption4)).check(matches(isNotChecked()));
    }

    @Test
    public void textview_Is_Displayed(){
        onView(withId(R.id.txtQno)).check(matches(isDisplayed()));
        onView(withId(R.id.txtQue)).check(matches(isDisplayed()));
        onView(withId(R.id.txtQueTitle)).check(matches(isDisplayed()));
    }

    @Test
    public void buttonClick(){
        onView(withId(R.id.btnNext)).perform(click());
    }

    @Test
    public void progressbar_Is_show(){
        onView(withId(R.id.progressQuiz)).check(matches(isDisplayed()));
    }

    @Test
    public void progressDialog_Is_Show(){
        onView(withId(R.id.btnNext)).perform(click());
        onView(withText("Wait!!! Question Loading...")).check(matches(isDisplayed()));
    }


    @Test
    public void testCheck_QuitQuiz_Dialog_Displayed() {
        // Click on the button that shows the dialog
        onView(withId(R.id.mnuStop)).perform(click());

        // Check the dialog title and message text is displayed
        onView(withText("Exit")).check(matches(isDisplayed()));
        onView(withText("You will end the quiz!!!")).check(matches(isDisplayed()));
    }

    @Test
    public void testClick_QuitButton() {
        onView(withId(R.id.mnuStop)).perform(click());

        // android.R.id.button1 = positive button
        onView(withId(android.R.id.button1)).perform(click());
    }

    @Test
    public void testCheck_LogoutDialog_Displayed() {
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
    public void rotateScreenTest(){
        rotateOrientation(mActivityRule.getActivity());
        //this test suppose to be failed cause of layout intentionally created as button not visible while screen on landscape mode
        onView(withId(R.id.btnNext)).check(matches(isDisplayed()));
    }

    private static void rotateToLandscape(Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    private static void rotateToPortrait(Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public static void rotateOrientation(Activity activity) {
        int currentOrientation = activity.getResources().getConfiguration().orientation;

        switch (currentOrientation) {
            case Configuration.ORIENTATION_LANDSCAPE:
                rotateToPortrait(activity);
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                rotateToLandscape(activity);
                break;
            default:
                rotateToLandscape(activity);
        }
    }

}