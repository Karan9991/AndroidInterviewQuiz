package com.test.quizexampleinterview.authentication

import android.animation.Animator
import android.animation.ValueAnimator
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.daasuu.ei.Ease
import com.daasuu.ei.EasingInterpolator
import com.google.firebase.auth.FirebaseAuth
import com.test.quizexampleinterview.*
import com.test.quizexampleinterview.authentication.SignIn

class SignIn : AppCompatActivity(), AuthContract.View, Validate {
    private var edtemail: EditText? = null
    private var edtpass: EditText? = null
    private val firebaseAuth: FirebaseAuth? = null
    private var isValid: Boolean? = null
    private var pbar: ProgressBar? = null
    private lateinit var button_login: View
    private var button_label: View? = null
    private var tvsignup: TextView? = null
    private var tvforgotpass: TextView? = null
    private lateinit var dm: DisplayMetrics
    private val va = ValueAnimator()
    private var authPresenter: AuthPresenter? = null
    private var authModel: AuthModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authPresenter = AuthPresenter(this, this, this)
        authPresenter!!.checkFirebaseLogin()
        setContentView(R.layout.activity_sign_in)
        isValid = false
        hideKeyboard(this)
        initUI()
        initAnimateButton()
        button_login!!.setOnClickListener {
            if (validations()) {
                authModel = AuthModel(edtemail!!.text.toString(), edtpass!!.text.toString())
                authPresenter!!.Login()
            }
        }
        tvforgotpass!!.setOnClickListener { startActivity(Intent(this@SignIn, Forgot_Password::class.java)) }
        tvsignup!!.setOnClickListener { startActivity(Intent(applicationContext, SignUp::class.java)) }
    }
//lateinit, others
    override val email: String
        get() = authModel!!.email

    override val password: String?
        get() = authModel!!.password

    override fun animateButton() {
        if (button_login!!.tag as Int == 1) {
            return
        } else if (button_login!!.tag as Int == 2) {
            button_login!!.animate().x(dm!!.widthPixels / 2.toFloat()).y(dm!!.heightPixels / 2.toFloat()).setInterpolator(EasingInterpolator(Ease.CUBIC_IN)).setListener(null).setDuration(1000).setStartDelay(0).start()
            button_login!!.animate().setStartDelay(600).setDuration(1000).scaleX(40f).scaleY(40f).setInterpolator(EasingInterpolator(Ease.CUBIC_IN_OUT)).start()
            return
        }
        button_login!!.tag = 1
        va.setFloatValues(button_login!!.measuredWidth.toFloat(), button_login!!.measuredHeight.toFloat())
        va.start()
        pbar!!.animate().setStartDelay(300).setDuration(1000).alpha(1f).start()
        button_label!!.animate().setStartDelay(100).setDuration(500).alpha(0f).start()
        button_login!!.animate().setInterpolator(FastOutSlowInInterpolator()).setStartDelay(4000).setDuration(1000).scaleX(30f).scaleY(30f).setListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p1: Animator) {
                pbar!!.animate().setStartDelay(0).setDuration(0).alpha(0f).start()
            }

            override fun onAnimationEnd(p1: Animator) {
                startActivity(Intent(this@SignIn, QuizDeskBoardActivity::class.java))
                finish()
            }

            override fun onAnimationCancel(p1: Animator) {
                // TODO: Implement this method
            }

            override fun onAnimationRepeat(p1: Animator) {
                // TODO: Implement this method
            }
        }).start()
    }

    override fun initAnimateButton() {
        dm = resources.displayMetrics
        button_login = findViewById(R.id.button_login)
        button_login.setTag(0)
        pbar!!.indeterminateDrawable.setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY)
        //StatusBarUtil.immersive(this)
        va.duration = 1500
        va.interpolator = DecelerateInterpolator()
        va.addUpdateListener { p1 ->
            val bt = button_login.getLayoutParams() as LinearLayout.LayoutParams
            bt.width = Math.round((p1.animatedValue as Float))
            button_login.setLayoutParams(bt)
        }
        button_login.animate().translationX(dm.widthPixels + button_login.getMeasuredWidth().toFloat()).setDuration(0).setStartDelay(0).start()
        button_login.animate().translationX(0f).setStartDelay(1000).setDuration(1500).setInterpolator(OvershootInterpolator()).start()
    }

    override fun initUI() {
        edtemail = findViewById(R.id.editTextsignemail)
        edtpass = findViewById(R.id.editTextsignpass)
        tvsignup = findViewById(R.id.textviewsignup)
        tvforgotpass = findViewById(R.id.tvforgotpass)
        pbar = findViewById(R.id.mainProgressBar1)
        button_label = findViewById(R.id.button_label)
    }

    override fun validations(): Boolean {
        if (Validator.isEmpty(edtemail!!.text.toString())) {
            edtemail!!.error = "E-Mail is required!"
            isValid = false
        } else if (!Validator.isValidEmail(edtemail!!.text.toString())) {
            edtemail!!.error = "Please enter a valid email address"
            isValid = false
        } else if (Validator.isEmpty(edtpass!!.text.toString())) {
            edtpass!!.error = "Password is required!"
            isValid = false
        } else {
            isValid = true
        }
        return isValid!!
    }

    companion object {
        fun hideKeyboard(activity: Activity?) {
            activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        }
    }
}