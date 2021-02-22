package com.test.quizexampleinterview.authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.daasuu.ei.Ease;
import com.daasuu.ei.EasingInterpolator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.test.quizexampleinterview.QuizDeskBoardActivity;
import com.test.quizexampleinterview.R;
import com.test.quizexampleinterview.StatusBarUtil;
import com.test.quizexampleinterview.Validate;
import com.test.quizexampleinterview.Validator;

public class SignIn extends AppCompatActivity implements AuthContract.View, Validate {

   private EditText edtemail,edtpass;
   private FirebaseAuth firebaseAuth;
   private Boolean isValid;
   private ProgressBar pbar;
   private View button_login, button_label;
   private TextView tvsignup,tvforgotpass;
   private DisplayMetrics dm;
   private final ValueAnimator va = new ValueAnimator();
   private AuthPresenter authPresenter;
   private AuthModel authModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkFirebaseLogin();

        setContentView(R.layout.activity_sign_in);

        isValid = false;
        authModel = new AuthModel();
        authPresenter = new AuthPresenter(this, this, this);

        hideKeyboard(this);

        initUI();
        initAnimateButton();

        button_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View p1) {

                if (validations()){
                    authModel.setEmail(edtemail.getText().toString());
                    authModel.setPassword(edtpass.getText().toString());
                    authPresenter.Login();
                }
             }

        });

        tvforgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignIn.this, Forgot_Password.class));
            }
        });
        tvsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SignUp.class));
            }
        });
    }

    @Override
    public String getEmail() {
        return authModel.getEmail();
    }

    @Override
    public String getPassword() {
        return authModel.getPassword();
    }

    @Override
    public void animateButton() {

    if ((int) button_login.getTag() == 1) {
        return;
    } else if ((int) button_login.getTag() == 2) {
        button_login.animate().x(dm.widthPixels / 2).y(dm.heightPixels / 2).setInterpolator(new EasingInterpolator(Ease.CUBIC_IN)).setListener(null).setDuration(1000).setStartDelay(0).start();
        button_login.animate().setStartDelay(600).setDuration(1000).scaleX(40).scaleY(40).setInterpolator(new EasingInterpolator(Ease.CUBIC_IN_OUT)).start();
        return;
    }
    button_login.setTag(1);
    va.setFloatValues(button_login.getMeasuredWidth(), button_login.getMeasuredHeight());
    va.start();
    pbar.animate().setStartDelay(300).setDuration(1000).alpha(1).start();

    button_label.animate().setStartDelay(100).setDuration(500).alpha(0).start();
    button_login.animate().setInterpolator(new FastOutSlowInInterpolator()).setStartDelay(4000).setDuration(1000).scaleX(30).scaleY(30).setListener(new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator p1) {
            pbar.animate().setStartDelay(0).setDuration(0).alpha(0).start();

        }

        @Override
        public void onAnimationEnd(Animator p1) {
            startActivity(new Intent(SignIn.this, QuizDeskBoardActivity.class));
            finish();
        }

        @Override
        public void onAnimationCancel(Animator p1) {
            // TODO: Implement this method
        }

        @Override
        public void onAnimationRepeat(Animator p1) {
            // TODO: Implement this method
        }
    }).start();
    }

    @Override
    public void initAnimateButton() {
        dm = getResources().getDisplayMetrics();
        button_login = findViewById(R.id.button_login);
        button_login.setTag(0);
        pbar.getIndeterminateDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        StatusBarUtil.immersive(this);

        va.setDuration(1500);
        va.setInterpolator(new DecelerateInterpolator());
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
            @Override
            public void onAnimationUpdate(ValueAnimator p1) {
                LinearLayout.LayoutParams bt =  (LinearLayout.LayoutParams)button_login.getLayoutParams();
                bt.width=Math.round((Float) p1.getAnimatedValue());
                button_login.setLayoutParams(bt);

            }
        });
        button_login.animate().translationX(dm.widthPixels+button_login.getMeasuredWidth()).setDuration(0).setStartDelay(0).start();
        button_login.animate().translationX(0).setStartDelay(1000).setDuration(1500).setInterpolator(new OvershootInterpolator()).start();
    }

    @Override
    public void initUI() {
        edtemail = findViewById(R.id.editTextsignemail);
        edtpass = findViewById(R.id.editTextsignpass);
        tvsignup = this.findViewById(R.id.textviewsignup);
        tvforgotpass = this.findViewById(R.id.tvforgotpass);
        pbar = findViewById(R.id.mainProgressBar1);
        button_label = findViewById(R.id.button_label);
    }

    @Override
    public void checkFirebaseLogin() {
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser!=null&&firebaseAuth.getCurrentUser().isEmailVerified()){
            startActivity(new Intent(SignIn.this,QuizDeskBoardActivity.class));
            finish();
        }
    }


    @Override
    public boolean validations() {
        if (Validator.isEmpty(edtemail.getText().toString())){
            edtemail.setError("E-Mail is required!");
            isValid = false;
        }
        else if(!Validator.isValidEmail(edtemail.getText().toString())){
            edtemail.setError("Please enter a valid email address");
            isValid = false;
        }
        else if (Validator.isEmpty(edtpass.getText().toString())){
            edtpass.setError("Password is required!");
            isValid = false;
        }
        else {
            isValid = true;
        }
        return isValid;
    }

    public static void hideKeyboard(Activity activity) {
        if (activity != null) {
            activity.getWindow()
                    .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        }
    }
}
