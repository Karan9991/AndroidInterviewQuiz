package com.test.quizexampleinterview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Forgot_Password extends AppCompatActivity {
EditText edtemailforpass;
Button sumbmit;
Boolean isValid;
private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot__password);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Forgot Password");

        isValid = false;

        edtemailforpass = (EditText)findViewById(R.id.editTextforpassemail);
        sumbmit = (Button)findViewById(R.id.buttonsubmit);

        firebaseAuth = FirebaseAuth.getInstance();




        sumbmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validation()) {
                        firebaseAuth.sendPasswordResetEmail(edtemailforpass.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    edtemailforpass.setError(null);
                                    Toast.makeText(Forgot_Password.this, "Password Link sent to your E-Mail, Please check your E-Mail", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(Forgot_Password.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });

            }

            }
        });
    }





    public boolean validation(){
        if (TextUtils.isEmpty(edtemailforpass.getText())){
            edtemailforpass.setError("Email is required");
        }
         if (!Patterns.EMAIL_ADDRESS.matcher(edtemailforpass.getText().toString().trim()).matches()){
            edtemailforpass.setError( "Please enter a valid email address");
        }

        else {
                     edtemailforpass.setError(null);
                     isValid = true;
        }
        return isValid;
    }

    @Override
    public boolean onSupportNavigateUp() {

        startActivity(new Intent(Forgot_Password.this,SignIn.class));

        return super.onSupportNavigateUp();
    }
}
