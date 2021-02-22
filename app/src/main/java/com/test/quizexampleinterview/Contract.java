package com.test.quizexampleinterview;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public interface Contract {

    interface View{
        void initActionBar();
        void initUI();
        default void blinkText(){}
        default void initTimer(){}
    }

    interface Presenter{
        void alertDialog(String title, String message, String positiveButton, String negativeButton, Context context, Class<?> cls);
        void navigateToAhead(Class<?> cls);
        void logout();
    }

}
