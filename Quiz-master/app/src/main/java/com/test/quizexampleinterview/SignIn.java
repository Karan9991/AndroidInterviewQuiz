package com.test.quizexampleinterview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daasuu.ei.Ease;
import com.daasuu.ei.EasingInterpolator;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity {
EditText edtemail,edtpass;
FirebaseAuth firebaseAuth;
Boolean isValid;
ProgressBar pbar;
View button_login, button_label;
TextView tvsignup,tvforgotpass;
private DisplayMetrics dm;
private FirebaseAuth.AuthStateListener mAuthstatelistener;
    final ValueAnimator va=new ValueAnimator();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        isValid = false;
       edtemail = (EditText)findViewById(R.id.editTextsignemail);
        edtpass = (EditText)findViewById(R.id.editTextsignpass);
        tvsignup = (TextView)this.findViewById(R.id.textviewsignup);
        tvforgotpass = (TextView)this.findViewById(R.id.tvforgotpass);

        hideKeyboard(this);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser!=null&&firebaseAuth.getCurrentUser().isEmailVerified()){
            startActivity(new Intent(SignIn.this,QuizDeskBoardActivity.class));
            finish();
        }
//        mAuthstatelistener = new FirebaseAuth.AuthStateListener() {
//            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                if (firebaseUser!=null){
//                    startActivity(new Intent(SignIn.this,QuizDeskBoardActivity.class));
//                }else {
//                    Toast.makeText(getApplicationContext(),"Please SignUp",Toast.LENGTH_SHORT).show();
//                }
//            }
//        };

        pbar=(ProgressBar) findViewById(R.id.mainProgressBar1);
        //  button_icon=findViewById(R.id.button_icon);
        button_label=findViewById(R.id.button_label);


        dm=getResources().getDisplayMetrics();
        button_login=findViewById(R.id.button_login);
        button_login.setTag(0);
        pbar.getIndeterminateDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        StatusBarUtil.immersive(this);

        //  frag_login=new LoginFragment();
        //  frag_dashboard=new DashboardFragment();
        //    getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, frag_login).commit();

        va.setDuration(1500);
        va.setInterpolator(new DecelerateInterpolator());
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
            @Override
            public void onAnimationUpdate(ValueAnimator p1) {
                LinearLayout.LayoutParams bt =  (LinearLayout.LayoutParams)button_login.getLayoutParams();
                //  RelativeLayout.LayoutParams button_login_lp= (RelativeLayout.LayoutParams) button_login.getLayoutParams();
                bt.width=Math.round((Float) p1.getAnimatedValue());
                button_login.setLayoutParams(bt);

            }
        });
        button_login.animate().translationX(dm.widthPixels+button_login.getMeasuredWidth()).setDuration(0).setStartDelay(0).start();
        button_login.animate().translationX(0).setStartDelay(6500).setDuration(1500).setInterpolator(new OvershootInterpolator()).start();

        //click
        button_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View p1) {

                if (validation()){
                    String email = edtemail.getText().toString();
                    String pwd = edtpass.getText().toString();
                    firebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(SignIn.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                if (firebaseAuth.getCurrentUser().isEmailVerified()){
                                    animateButton();
                                }else {
                                    Toast.makeText(SignIn.this,"Please verify your E-Mail address", Toast.LENGTH_LONG).show();

                                }
                            } else {
                                Toast.makeText(SignIn.this, "SignIn Failed " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
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
                startActivity(new Intent(getApplicationContext(),SignUp.class));
            }
        });
    }
public void animateButton(){

    if ((int) button_login.getTag() == 1) {
        return;
    } else if ((int) button_login.getTag() == 2) {
        button_login.animate().x(dm.widthPixels / 2).y(dm.heightPixels / 2).setInterpolator(new EasingInterpolator(Ease.CUBIC_IN)).setListener(null).setDuration(1000).setStartDelay(0).start();
        button_login.animate().setStartDelay(600).setDuration(1000).scaleX(40).scaleY(40).setInterpolator(new EasingInterpolator(Ease.CUBIC_IN_OUT)).start();
        //  button_icon.animate().alpha(0).rotation(90).setStartDelay(0).setDuration(800).start();
        return;
    }
    button_login.setTag(1);
    va.setFloatValues(button_login.getMeasuredWidth(), button_login.getMeasuredHeight());
    va.start();
    pbar.animate().setStartDelay(300).setDuration(1000).alpha(1).start();
    //1
    button_label.animate().setStartDelay(100).setDuration(500).alpha(0).start();
    button_login.animate().setInterpolator(new FastOutSlowInInterpolator()).setStartDelay(4000).setDuration(1000).scaleX(30).scaleY(30).setListener(new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator p1) {
            pbar.animate().setStartDelay(0).setDuration(0).alpha(0).start();

        }

        @Override
        public void onAnimationEnd(Animator p1) {
            //  nextActivity();
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

    public boolean validation(){
        if (TextUtils.isEmpty(edtemail.getText())){
            edtemail.setError("Email is required");
        }
        else if (TextUtils.isEmpty(edtpass.getText())){
            edtpass.setError("Password is required");
        }
        else if (TextUtils.isEmpty(edtemail.getText())&&TextUtils.isEmpty(edtpass.getText())){
            edtemail.setError("Email is required");
            edtpass.setError("Password is required");
        }
        else {
            edtemail.setError(null);
            edtpass.setError(null);
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
