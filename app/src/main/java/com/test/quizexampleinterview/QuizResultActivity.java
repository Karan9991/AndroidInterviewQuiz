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
import com.test.quizexampleinterview.authentication.SignIn;

public class QuizResultActivity extends AppCompatActivity implements Contract.View
{
    private TextView txtScore, txtResult, txtGreetings,edt;
    private Button btnplagain;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        presenter = new Presenter(this, this);

        initActionBar();
        initUI();

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
               presenter.navigateToAhead(QuizDeskBoardActivity.class);
           }
       });
    }

    @Override
    public void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.trophyyy);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    @Override
    public void initUI() {
        txtScore = (TextView)findViewById(R.id.txtScore);
        txtResult = (TextView)findViewById(R.id.txtResult);
        txtGreetings = (TextView)findViewById(R.id.txtGreetings);
        edt = (TextView)findViewById(R.id.editText);
        btnplagain = (Button)findViewById(R.id.playagainbtn);
    }

    @Override
    public void blinkText() {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(50);
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        txtGreetings.setAnimation(anim);
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
            presenter.logout();
        }
        return super.onOptionsItemSelected(item);
    }
}
