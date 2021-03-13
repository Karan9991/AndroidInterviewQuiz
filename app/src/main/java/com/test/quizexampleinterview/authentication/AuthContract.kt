package com.test.quizexampleinterview.authentication

interface AuthContract {
    interface View {
        fun initUI()
        val email: String?
        val password: String?
            get() = null

        fun initAnimateButton() {}
        fun animateButton() {}
    }

    interface Presenter {
        fun Login()
        fun SignUp()
        fun ForgotPassword()
        fun checkFirebaseLogin()
    }
}