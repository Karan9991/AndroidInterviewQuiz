package com.test.quizexampleinterview;

import android.os.SystemClock;
import android.webkit.WebView;

import androidx.test.espresso.web.webdriver.Locator;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.web.assertion.WebViewAssertions.webMatches;
import static androidx.test.espresso.web.model.Atoms.getCurrentUrl;
import static androidx.test.espresso.web.sugar.Web.onWebView;
import static androidx.test.espresso.web.webdriver.DriverAtoms.findElement;
import static androidx.test.espresso.web.webdriver.DriverAtoms.getText;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

public class QuizInstructionActivityTest {

    @Rule
    public ActivityTestRule<QuizInstructionActivity> mActivityRule = new ActivityTestRule<>(QuizInstructionActivity.class);

    @Test
    public void webviewIsDisplayed(){
        SystemClock.sleep(1500);
        onWebView(withId(R.id.webViewInstruction)).forceJavascriptEnabled();

        onWebView().withElement(findElement(Locator.ID, "v")).check(webMatches(getText(), containsString("The quizzes consists of questions carefully designed to help you self-assess your comprehension of the information presented on the topics covered in the module. No data will be collected on the website regarding your responses or how many times you take the quiz.")));
    }


    @Test
    public void backBtnClick(){
        onView(withId(R.id.btnInstruction)).perform(click());
    }

}