package com.test.quizexampleinterview;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity  {
    Button login;
    EditText editTextEmail, editTextPassword;
    CheckBox chkRemember;
    Button btnLogin;
    SharedPreferences sharedPreferences;
    String email,pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = (EditText) findViewById(R.id.edtEmail);
        editTextPassword = (EditText) findViewById(R.id.edtPassword);
        chkRemember = (CheckBox) findViewById(R.id.chkRemember);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        sharedPreferences = getSharedPreferences("userDetails",MODE_PRIVATE);
         email = sharedPreferences.getString("userEmail",null);
         pwd = sharedPreferences.getString("userPassword",null);

        if(email != null) {
            editTextEmail.setText(email.toString());
        }else {
            editTextEmail.setText(null);
        }
        if(pwd != null) {
            editTextPassword.setText(pwd.toString());
        }else {
            editTextPassword.setText(null);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chkRemember.isChecked()) {
                    SharedPreferences.Editor mEditor = sharedPreferences.edit();
                    mEditor.putString("userEmail", editTextEmail.getText().toString());
                    mEditor.putString("userPassword", editTextPassword.getText().toString());
                    mEditor.apply();
                } else {
                    SharedPreferences.Editor mEditor = sharedPreferences.edit();
                    mEditor.putString("userEmail", null);
                    mEditor.putString("userPassword", null);
                    mEditor.apply();
                }

                if (editTextEmail.getText().toString().equals("admin") &&
                        editTextPassword.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(),
                            "Redirecting... ", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), QuizDeskBoardActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected void onResume() {
        sharedPreferences = getSharedPreferences("userDetails",MODE_PRIVATE);
        setSavedDetails();
        super.onResume();
    }
    private void setSavedDetails() {
        String email = sharedPreferences.getString("userEmail", "");
        String pwd = sharedPreferences.getString("userPassword", "");
        if (email != null && pwd != null) {
            editTextEmail.setText(email.toString());
            editTextPassword.setText(pwd.toString());
            chkRemember.setChecked(true);
        }
    }
}
