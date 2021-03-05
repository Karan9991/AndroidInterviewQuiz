package com.test.quizexampleinterview.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.test.quizexampleinterview.R;
import com.test.quizexampleinterview.Validate;
import com.test.quizexampleinterview.Validator;

public class SignUp extends AppCompatActivity implements Validate, AuthContract.View {

    private Button btnsignup;
    private EditText edtemail,edtpass,edtcpass;
    private TextView tv;
    private ProgressBar progressBar;
    private AuthPresenter authPresenter;
    private boolean isValid;
    private AuthModel authModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        isValid = false;

        initUI();

        authPresenter = new AuthPresenter(this, this, this, progressBar);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validations()){
                    authModel = new AuthModel(edtemail.getText().toString(), edtpass.getText().toString());
                    authPresenter.SignUp();
                }
            }
        });

    tv.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(SignUp.this, SignIn.class));
        }
    });
    }


    @Override
    public boolean validations() {
        String p = edtpass.getText().toString().trim();
        String cp = edtcpass.getText().toString().trim();
        if (Validator.isEmpty(edtemail.getText().toString())){
            edtemail.setError("Email is required");
        }
        else if (!Validator.isValidEmail(edtemail.getText().toString())){
            edtemail.setError( "Please enter a valid email address");
        }
        else if (Validator.isEmpty(edtpass.getText().toString())){
            edtpass.setError("Password is required");
        }
        else if (Validator.isEmpty(edtcpass.getText().toString())){
            edtcpass.setError("Confirm Password is required");
        }
        else if(!Validator.isValidPassword(edtpass.getText().toString())){
            edtpass.setError( "Password must between 8 and 20 characters; must contain at least one lowercase letter, one uppercase letter, one numeric digit, and one special character, but cannot contain whitespace" );
            isValid = false;
        }
        else if (!p.equals(cp)) {
            edtpass.setError("Passwords Mismatch");
            edtcpass.setError("Passwords Mismatch");
        }
        else {
            isValid = true;
        }
        return isValid;
    }

    @Override
    public void initUI() {
        edtemail = findViewById(R.id.editTextsignemail);
        edtpass = findViewById(R.id.editTextsignpass);
        edtcpass = findViewById(R.id.editTextsigncnfrmpass);
        btnsignup = findViewById(R.id.buttonsignup);
        tv = findViewById(R.id.textviewsignup);
        progressBar = findViewById(R.id.progressBar_cyclic);
        progressBar.setVisibility(View.GONE);
        progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.progressbarcolor), PorterDuff.Mode.MULTIPLY);

    }

    @Override
    public String getEmail() {
        return authModel.getEmail();
    }

    @Override
    public String getPassword() {
        return authModel.getPassword();
    }

}
