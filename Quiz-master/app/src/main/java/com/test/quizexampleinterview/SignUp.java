package com.test.quizexampleinterview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {
Button btnsignup;
EditText edtemail,edtpass,edtcpass;
TextView tv;
private ProgressBar progressBar;
FirebaseAuth firebaseAuth;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{6,}" +               //at least 4 characters
                    "$");
    Boolean isValid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
 isValid = false;
     edtemail = (EditText)findViewById(R.id.editTextsignemail);
     edtpass = (EditText)findViewById(R.id.editTextsignpass);
     edtcpass = (EditText)findViewById(R.id.editTextsigncnfrmpass);
     btnsignup = (Button) findViewById(R.id.buttonsignup);
        tv = (TextView) findViewById(R.id.textviewsignup);
        progressBar = (ProgressBar)findViewById(R.id.progressBar_cyclic);
        progressBar.setVisibility(View.GONE);
     //   progressBar.setBackgroundColor(Color.BLUE);
        progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.progressbarcolor), PorterDuff.Mode.MULTIPLY);

        firebaseAuth = FirebaseAuth.getInstance();

     btnsignup.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             final String email = edtemail.getText().toString();
             String pwd = edtpass.getText().toString();
             if (validation()){
                 progressBar.setVisibility(View.VISIBLE);
                 firebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         progressBar.setVisibility(View.GONE);
                         if (task.isSuccessful()) {
                             firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                 @Override
                                 public void onComplete(@NonNull Task<Void> task) {
                                   if (task.isSuccessful()){
                                       Toast.makeText(getApplicationContext(), "Registered Successfully Please check your E-Mail for verification", Toast.LENGTH_LONG).show();
                                     edtemail.setText("");
                                     edtpass.setText("");
                                     edtcpass.setText("");
                                       // startActivity(new Intent(SignUp.this, SignIn.class));
                                       //finish();
                                   }else {
                                       Toast.makeText(getApplicationContext(),task.getException().getMessage(), Toast.LENGTH_LONG).show();

                                   }
                                 }
                             });

                         } else {
                             Toast.makeText(getApplicationContext(), "SignUp Unsuccessful " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                         }
                     }
                 });
         }
         }
     });

    tv.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(SignUp.this,SignIn.class));
        }
    });
    }

    public boolean validation(){
        String p = edtpass.getText().toString().trim();
        String cp = edtcpass.getText().toString().trim();
      if (TextUtils.isEmpty(edtemail.getText())){
          edtemail.setError("Email is required");
      }
      else if (!Patterns.EMAIL_ADDRESS.matcher(edtemail.getText().toString().trim()).matches()){
          edtemail.setError( "Please enter a valid email address");
      }
       else if (TextUtils.isEmpty(edtpass.getText())){
            edtpass.setError("Password is required");
        }
       else if (TextUtils.isEmpty(edtcpass.getText())){
            edtcpass.setError("Confirm Password is required");
        }
      else if(!PASSWORD_PATTERN.matcher(edtpass.getText().toString().trim()).matches()){
          edtpass.setError( "Password too weak" );
      }
      else if (!p.equals(cp)) {
          edtpass.setError("Passwords Mismatch");
          edtcpass.setError("Passwords Mismatch");
      }
      else {
          edtemail.setError(null);
          edtpass.setError(null);
          edtcpass.setError(null);
          isValid = true;
      }
      return isValid;
    }
}
