package com.test.quizexampleinterview.authentication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.test.quizexampleinterview.R
import com.test.quizexampleinterview.Validate
import com.test.quizexampleinterview.Validator

class Forgot_Password : AppCompatActivity(), Validate, AuthContract.View {
    private var edtemailforpass: EditText? = null
    private var sumbmit: Button? = null
    private var isValid: Boolean? = null
    private var authPresenter: AuthPresenter? = null
    private var authModel: AuthModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot__password)
        isValid = false
        initUI()
        authPresenter = AuthPresenter(this, this, this)
        sumbmit!!.setOnClickListener { view: View? ->
            if (validations()) {
                authModel = AuthModel(edtemailforpass!!.text.toString())
                authPresenter!!.ForgotPassword()
            }
        }
    }

    override fun validations(): Boolean {
        if (Validator.isEmpty(edtemailforpass!!.text.toString())) {
            edtemailforpass!!.error = "E-Mail is required!"
            isValid = false
        } else if (!Validator.isValidEmail(edtemailforpass!!.text.toString())) {
            edtemailforpass!!.error = "Please enter a valid email address"
            isValid = false
        } else {
            isValid = true
        }
        return isValid!!
    }

    override fun initUI() {
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Forgot Password"
        edtemailforpass = findViewById(R.id.editTextforpassemail)
        sumbmit = findViewById(R.id.buttonsubmit)
    }

    override val email: String
        get() = authModel!!.email

    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this@Forgot_Password, SignIn::class.java))
        return super.onSupportNavigateUp()
    }
}