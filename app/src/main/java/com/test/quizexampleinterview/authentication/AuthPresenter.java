package com.test.quizexampleinterview.authentication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.test.quizexampleinterview.QuizDeskBoardActivity;

public class AuthPresenter implements AuthContract.Presenter{

    private FirebaseAuth firebaseAuth;
    private Activity mActivity;
    private Context mContext;
    private ProgressBar progressBar;
    private final AuthContract.View view;

    public AuthPresenter(AuthContract.View view, Activity activity, Context context) {
        firebaseAuth = FirebaseAuth.getInstance();
        this.view = view;
        this.mActivity = activity;
        this.mContext = context;
    }

    public AuthPresenter(AuthContract.View view, Activity activity, Context context, ProgressBar progressBar) {
        firebaseAuth = FirebaseAuth.getInstance();
        this.view = view;
        this.mActivity = activity;
        this.mContext = context;
        this.progressBar = progressBar;
    }

    @Override
    public void Login() {
        firebaseAuth.signInWithEmailAndPassword(view.getEmail(), view.getPassword()).addOnCompleteListener(mActivity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    if (firebaseAuth.getCurrentUser().isEmailVerified()){
                        view.animateButton();
                    }else {
                        Toast.makeText(mContext,"Please verify your E-Mail address", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(mContext, "SignIn Failed " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void SignUp() {
        progressBar.setVisibility(View.VISIBLE);
        firebaseAuth.createUserWithEmailAndPassword(view.getEmail(), view.getPassword()).addOnCompleteListener(mActivity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(mContext, "Registered Successfully Please check your E-Mail for verification", Toast.LENGTH_LONG).show();

                            }else {
                                Toast.makeText(mContext,task.getException().getMessage(), Toast.LENGTH_LONG).show();

                            }
                        }
                    });

                } else {
                    Toast.makeText(mContext, "SignUp Unsuccessful " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void ForgotPassword() {
        firebaseAuth.sendPasswordResetEmail(view.getEmail()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(mContext, "Password Link sent to your E-Mail, Please check your E-Mail", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(mContext, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void checkFirebaseLogin() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser!=null&&firebaseAuth.getCurrentUser().isEmailVerified()){
            mContext.startActivity(new Intent(mContext, QuizDeskBoardActivity.class));
            ((Activity)mContext).finish();
        }
    }
}
