package com.test.quizexampleinterview.authentication

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.test.quizexampleinterview.R
import com.test.quizexampleinterview.Validate
import com.test.quizexampleinterview.Validator
import com.test.quizexampleinterview.authentication.SignIn

class SignUp : AppCompatActivity(), Validate, AuthContract.View {
    private var btnsignup: Button? = null
    private var edtemail: EditText? = null
    private var edtpass: EditText? = null
    private var edtcpass: EditText? = null
    private var tv: TextView? = null
    private lateinit var progressBar: ProgressBar
    private var authPresenter: AuthPresenter? = null
    private var isValid = false
    private var authModel: AuthModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        isValid = false
        initUI()
        authPresenter = AuthPresenter(this, this, this, progressBar)
        btnsignup!!.setOnClickListener {
            if (validations()) {
                authModel = AuthModel(edtemail!!.text.toString(), edtpass!!.text.toString())
                authPresenter!!.SignUp()
            }
        }
        tv!!.setOnClickListener { startActivity(Intent(this@SignUp, SignIn::class.java)) }
    }

    override fun validations(): Boolean {
        val p = edtpass!!.text.toString().trim { it <= ' ' }
        val cp = edtcpass!!.text.toString().trim { it <= ' ' }
        if (Validator.isEmpty(edtemail!!.text.toString())) {
            edtemail!!.error = "Email is required"
        } else if (!Validator.isValidEmail(edtemail!!.text.toString())) {
            edtemail!!.error = "Please enter a valid email address"
        } else if (Validator.isEmpty(edtpass!!.text.toString())) {
            edtpass!!.error = "Password is required"
        } else if (Validator.isEmpty(edtcpass!!.text.toString())) {
            edtcpass!!.error = "Confirm Password is required"
        } else if (!Validator.isValidPassword(edtpass!!.text.toString())) {
            edtpass!!.error = "Password must between 8 and 20 characters; must contain at least one lowercase letter, one uppercase letter, one numeric digit, and one special character, but cannot contain whitespace"
            isValid = false
        } else if (p != cp) {
            edtpass!!.error = "Passwords Mismatch"
            edtcpass!!.error = "Passwords Mismatch"
        } else {
            isValid = true
        }
        return isValid
    }

    override fun initUI() {
        edtemail = findViewById(R.id.editTextsignemail)
        edtpass = findViewById(R.id.editTextsignpass)
        edtcpass = findViewById(R.id.editTextsigncnfrmpass)
        btnsignup = findViewById(R.id.buttonsignup)
        tv = findViewById(R.id.textviewsignup)
        progressBar = findViewById(R.id.progressBar_cyclic)
        progressBar.setVisibility(View.GONE)
        progressBar.getIndeterminateDrawable().setColorFilter(resources.getColor(R.color.progressbarcolor), PorterDuff.Mode.MULTIPLY)
    }

    override val email: String
        get() = authModel!!.email

    override val password: String?
        get() = authModel!!.password
}