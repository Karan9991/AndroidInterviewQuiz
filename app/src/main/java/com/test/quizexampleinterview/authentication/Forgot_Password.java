package com.test.quizexampleinterview.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.test.quizexampleinterview.R;
import com.test.quizexampleinterview.Validate;
import com.test.quizexampleinterview.Validator;

public class Forgot_Password extends AppCompatActivity implements Validate, AuthContract.View {

    private EditText edtemailforpass;
    private Button sumbmit;
    private Boolean isValid;
    private AuthPresenter authPresenter;
    private AuthModel authModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot__password);

        isValid = false;

        initUI();

        authModel = new AuthModel();
        authPresenter = new AuthPresenter(this, this, this);

        sumbmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validations()) {
                    authModel.setEmail(edtemailforpass.getText().toString());
                    authPresenter.ForgotPassword();
            }
            }
        });
    }

    @Override
    public boolean validations() {
        if (Validator.isEmpty(edtemailforpass.getText().toString())){
            edtemailforpass.setError("E-Mail is required!");
            isValid = false;
        }
        else if(!Validator.isValidEmail(edtemailforpass.getText().toString())){
            edtemailforpass.setError("Please enter a valid email address");
            isValid = false;
        }
        else {
            isValid = true;
        }
        return isValid;
    }

    @Override
    public void initUI() {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Forgot Password");
        edtemailforpass = findViewById(R.id.editTextforpassemail);
        sumbmit = findViewById(R.id.buttonsubmit);
    }

    @Override
    public String getEmail() {
        return authModel.getEmail();
    }

    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(Forgot_Password.this, SignIn.class));
        return super.onSupportNavigateUp();
    }

}
