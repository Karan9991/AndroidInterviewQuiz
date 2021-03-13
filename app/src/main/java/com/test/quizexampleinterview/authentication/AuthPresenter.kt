package com.test.quizexampleinterview.authentication

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.test.quizexampleinterview.QuizDeskBoardActivity

class AuthPresenter : AuthContract.Presenter {
    private var firebaseAuth: FirebaseAuth
    private var mActivity: Activity
    private var mContext: Context
    private var progressBar: ProgressBar? = null
    private val view: AuthContract.View

    constructor(view: AuthContract.View, activity: Activity, context: Context) {
        firebaseAuth = FirebaseAuth.getInstance()
        this.view = view
        mActivity = activity
        mContext = context
    }

    constructor(view: AuthContract.View, activity: Activity, context: Context, progressBar: ProgressBar?) {
        firebaseAuth = FirebaseAuth.getInstance()
        this.view = view
        mActivity = activity
        mContext = context
        this.progressBar = progressBar
    }

    override fun Login() {
        firebaseAuth.signInWithEmailAndPassword(view.email!!, view.password!!).addOnCompleteListener(mActivity) { task ->
            if (task.isSuccessful) {
                if (firebaseAuth.currentUser!!.isEmailVerified) {
                    view.animateButton()
                } else {
                    Toast.makeText(mContext, "Please verify your E-Mail address", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(mContext, "SignIn Failed " + task.exception!!.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun SignUp() {
        progressBar!!.visibility = View.VISIBLE
        firebaseAuth.createUserWithEmailAndPassword(view.email!!, view.password!!).addOnCompleteListener(mActivity) { task ->
            progressBar!!.visibility = View.GONE
            if (task.isSuccessful) {
                firebaseAuth.currentUser!!.sendEmailVerification().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(mContext, "Registered Successfully Please check your E-Mail for verification", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(mContext, task.exception!!.message, Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Toast.makeText(mContext, "SignUp Unsuccessful " + task.exception!!.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun ForgotPassword() {
        firebaseAuth.sendPasswordResetEmail(view.email!!).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(mContext, "Password Link sent to your E-Mail, Please check your E-Mail", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(mContext, task.exception!!.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun checkFirebaseLogin() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null && firebaseAuth.currentUser!!.isEmailVerified) {
            mContext.startActivity(Intent(mContext, QuizDeskBoardActivity::class.java))
            (mContext as Activity).finish()
        }
    }
}