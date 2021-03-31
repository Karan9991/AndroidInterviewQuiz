package com.test.quizexampleinterview

import android.content.Context

interface Contract {
    interface View {
        fun initActionBar()
        fun initUI()
        fun blinkText() {}
        fun initTimer() {}
    }

    interface Presenter {
        fun alertDialog(title: String?, message: String?, positiveButton: String?, negativeButton: String?, context: Context?, cls: Class<*>?)
        fun navigateToAhead(cls: Class<*>?)
        fun logout()
    }
}