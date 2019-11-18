package com.test.quizexampleinterview;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class QuizResultActivity extends AppCompatActivity
{
    TextView txtScore, txtResult, txtGreetings,edt;
    Button btnplagain;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        intiActionBar();

        txtScore = (TextView)findViewById(R.id.txtScore);
        txtResult = (TextView)findViewById(R.id.txtResult);
        txtGreetings = (TextView)findViewById(R.id.txtGreetings);
        edt = (TextView)findViewById(R.id.editText);
        btnplagain = (Button)findViewById(R.id.playagainbtn);
        int score = getIntent().getExtras().getInt("result",0);
        txtScore.setText("Score : " + score);
        blinkText();
        if(score <5)
        {
            txtScore.setTextColor(Color.RED);
            txtResult.setText("Fail");
            txtGreetings.setText("Bad Luck!!!");
        }
       else
        {
            txtScore.setTextColor(Color.GREEN);
            txtResult.setText("Pass");
            txtGreetings.setText("Congratulation!!!");
        }


        SharedPreferences sharedPreferences=getPreferences(MODE_PRIVATE);
        int highscore = sharedPreferences.getInt("highscore",0);
        if(highscore>=score) {
            edt.setText("Highest Score : "+highscore);
          //  Toast.makeText(this,"High Score : " + highscore,Toast.LENGTH_LONG).show();
        }
       else {
           edt.setText("New Score : "+score);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putInt("highscore",score);
            editor.commit();
        }
btnplagain.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(QuizResultActivity.this, QuizDeskBoardActivity.class));
    }
});

    }

    private void intiActionBar()
    {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.trophyyy);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    private void blinkText(){
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(50); //You can manage the blinking time with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        txtGreetings.setAnimation(anim);
    }
    public void logout(){
        AlertDialog.Builder b = new AlertDialog.Builder(QuizResultActivity.this)
                .setTitle("Sign Out")
                .setPositiveButton("Sign Out", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(QuizResultActivity.this, SignIn.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                    }
                })
                .setMessage("Are you sure you would like to sign out?");
        b.create().show();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == R.id.logout)
        {
            logout();
        }
        return super.onOptionsItemSelected(item);
    }
}
