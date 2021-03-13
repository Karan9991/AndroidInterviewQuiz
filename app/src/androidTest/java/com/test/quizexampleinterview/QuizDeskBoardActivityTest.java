package com.test.quizexampleinterview;

import android.os.SystemClock;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class QuizDeskBoardActivityTest  {

    @Rule
    public ActivityTestRule<QuizDeskBoardActivity> mActivityRule = new ActivityTestRule<>(QuizDeskBoardActivity.class);

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void textviewIsDisplayed(){
        SystemClock.sleep(1500);

        String a = mActivityRule.getActivity().getString(R.string.desk_inst);
        onView(withText(a)).check(matches(isDisplayed()));
    }

    @Test
    public void btnInstructionClick(){
        onView(withId(R.id.btnInstruction)).perform(click());

    }

    @Test
    public void testCheckDialogDisplayed() {
        // Click on the button that shows the dialog
        onView(withId(R.id.btnStart)).perform(click());

        // Check the dialog title text is displayed
        onView(withText("Start Quiz")).check(matches(isDisplayed()));
        onView(withText("All the Best!!!")).check(matches(isDisplayed()));

    }

    @Test
    public void testClickOkButton() {
        onView(withId(R.id.btnStart)).perform(click());

        // android.R.id.button1 = positive button
        onView(withId(android.R.id.button1)).perform(click());
    }

    @Test
    public void testClickCancelButton() {
        onView(withId(R.id.btnStart)).perform(click());

        // android.R.id.button1 = positive button
        onView(withId(android.R.id.button2)).perform(click());
    }

    @Test
    public void startQuizTest_GiveAnswers_GetResult(){
        onView(withId(R.id.btnStart)).perform(click());

        // android.R.id.button1 = positive button
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.rbOption1)).perform(click());
        onView(withId(R.id.btnNext)).perform(click());
        SystemClock.sleep(4000);

        onView(withId(R.id.rbOption2)).perform(click());
        onView(withId(R.id.btnNext)).perform(click());
        SystemClock.sleep(4000);

        onView(withId(R.id.rbOption3)).perform(click());
        onView(withId(R.id.btnNext)).perform(click());
        SystemClock.sleep(4000);

        onView(withId(R.id.rbOption4)).perform(click());
        onView(withId(R.id.btnNext)).perform(click());
        SystemClock.sleep(4000);

        onView(withId(R.id.rbOption1)).perform(click());
        onView(withId(R.id.btnNext)).perform(click());
        SystemClock.sleep(4000);

        onView(withId(R.id.rbOption2)).perform(click());
        onView(withId(R.id.btnNext)).perform(click());
        SystemClock.sleep(4000);

        onView(withId(R.id.rbOption1)).perform(click());
        onView(withId(R.id.btnNext)).perform(click());
        SystemClock.sleep(4000);

        onView(withId(R.id.rbOption1)).perform(click());
        onView(withId(R.id.btnNext)).perform(click());
        SystemClock.sleep(4000);

        onView(withId(R.id.rbOption1)).perform(click());
        onView(withId(R.id.btnNext)).perform(click());
        SystemClock.sleep(4000);

        onView(withId(R.id.rbOption4)).perform(click());
        onView(withId(R.id.btnNext)).perform(click());
        SystemClock.sleep(7000);

    }

    @Test
    public void startQuiz_ExitQuizTest_InBetween() {
        onView(withId(R.id.btnStart)).perform(click());

        // android.R.id.button1 = positive button
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.rbOption1)).perform(click());
        onView(withId(R.id.btnNext)).perform(click());
        SystemClock.sleep(4000);

        onView(withId(R.id.rbOption2)).perform(click());
        onView(withId(R.id.btnNext)).perform(click());
        SystemClock.sleep(4000);

        onView(withId(R.id.rbOption3)).perform(click());
        onView(withId(R.id.btnNext)).perform(click());
        SystemClock.sleep(4000);

        onView(withId(R.id.mnuStop)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        SystemClock.sleep(4000);

    }
}