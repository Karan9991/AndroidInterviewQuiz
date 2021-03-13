package com.test.quizexampleinterview;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.test.quizexampleinterview.database.DbHelper;
import com.test.quizexampleinterview.model.Questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class QuizMainActivity extends AppCompatActivity implements Contract.View
{
    Button btnNext;
    TextView txtTimer, txtQue;
    CountDownTimer mCountDownTimer;
    List<Questions> queList;
    static int currentQue;
    Animation anim;
    ArrayList<Integer> questionSequence;
    RadioGroup mRadioGroup;
    RadioButton rbOption1, rbOption2, rbOption3, rbOption4;
    ProgressDialog mProgressDialog;
    ProgressBar mProgressBarQuiz;
    TextView txtQno, txtQueTitle;
    static int score;
    Questions currentQuestion;
    private Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new Presenter(this, this);

        currentQue = 0;

        initUI();
        initActionBar();
        initTimer();
        setUpQuizQuestions();
        blinkText();
        getUniqueRandomNumbers();
        getNextQuestion();
    }

    @Override
    public void initTimer() {
        mCountDownTimer = new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                long sec = millisUntilFinished / 1000;
                txtTimer.setText(sec + " Sec");
                txtTimer.setTextColor(Color.BLACK);
                if(sec <=5)
                {
                    txtTimer.setTextColor(Color.RED);
                    anim.setRepeatCount(Animation.INFINITE);
                    blinkText();
                }else{
                    anim.setRepeatCount(0);
                }
            }

            public void onFinish() {
                txtTimer.setText("Done!");
                getNextQuestion();
            }
        };
    }

    private void setUpQuizQuestions()
    {
        queList =  DbHelper.getInstance(this).getAllQuestions();
    }

    private View.OnClickListener onButtonClick = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            switch (v.getId())
            {
                case  R.id.btnNext:
                    mCountDownTimer.cancel();
                    mProgressDialog.show();
                    Thread timerThread = new Thread(){
                        public void run(){
                            try{
                                sleep(3000);
                            }catch(InterruptedException e){
                                e.printStackTrace();
                            }finally{
                                runOnUiThread(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        mProgressDialog.hide();
                                        getNextQuestion();
                                    }
                                });
                            }
                        }
                    };
                    timerThread.start();
                    break;

                default:
                    Toast.makeText(getApplicationContext(),((Button)v).getText().toString(),Toast.LENGTH_SHORT).show();

            }
        }
    };

    private void exitQuiz()
    {
        currentQue = 0;
        mCountDownTimer.cancel();

        AlertDialog.Builder b = new AlertDialog.Builder(QuizMainActivity.this)
                .setTitle("Exit")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                        finish();
                        sendResult();
                    }
                })
                .setMessage("You will end the quiz!!!");
        b.create().show();
    }

    private void getNextQuestion()
    {
        if(currentQue<queList.size())
        {
            int c = questionSequence.get(currentQue);
            currentQue++;
            mProgressBarQuiz.setProgress(currentQue);
            txtQno.setText(currentQue + "/" + queList.size());
            currentQuestion = queList.get(c);
            txtQueTitle.setText("[ Q : " + currentQue + " ] ");
            txtQue.setText(currentQuestion.getQUESTION());

            mRadioGroup.clearCheck();

            rbOption1.setText(currentQuestion.getOPTA());
            rbOption2.setText(currentQuestion.getOPTB());
            rbOption3.setText(currentQuestion.getOPTC());
            rbOption4.setText(currentQuestion.getOPTD());
            mCountDownTimer.start();
        }else
        {
            mProgressDialog.setMessage("Cool!!! Preparing results....");
            mProgressDialog.show();
            Thread timerThread = new Thread(){
                public void run(){
                    try{
                        sleep(3000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }finally{
                        runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                mProgressDialog.hide();
                                sendResult();
                            }
                        });
                    }
                }
            };
            timerThread.start();
        }
    }

    private void sendResult() {
        Intent intent = new Intent(getApplicationContext(),QuizResultActivity.class);
        intent.putExtra("result",score);
        startActivity(intent);
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
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Wait!!! Question Loading...");
        mProgressDialog.setCancelable(false);

        mProgressBarQuiz = (ProgressBar)findViewById(R.id.progressQuiz);
        txtQno = (TextView)findViewById(R.id.txtQno);

        txtTimer = (TextView)findViewById(R.id.txtTimer);
        txtQue = (TextView)findViewById(R.id.txtQue);
        txtQueTitle = (TextView)findViewById(R.id.txtQueTitle);
        txtTimer.setText("00 Sec");

        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(onButtonClick);

        mRadioGroup = (RadioGroup)findViewById(R.id.rbGroup);
        rbOption1 = (RadioButton)findViewById(R.id.rbOption1);
        rbOption2 = (RadioButton)findViewById(R.id.rbOption2);
        rbOption3 = (RadioButton)findViewById(R.id.rbOption3);
        rbOption4 = (RadioButton)findViewById(R.id.rbOption4);

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                String ans="";
                switch (checkedId)
                {
                    case R.id.rbOption1:
                        ans = rbOption1.getText().toString();
                        break;
                    case R.id.rbOption2:
                        ans = rbOption2.getText().toString();
                        break;
                    case R.id.rbOption3:
                        ans = rbOption3.getText().toString();
                        break;
                    case R.id.rbOption4:
                        ans = rbOption4.getText().toString();
                        break;
                }

                if(currentQuestion.getANSWER().equalsIgnoreCase(ans))
                {
                    score++;
                }
                Log.d("RES", "onCheckedChanged: " + ans + "[" + score + "]");
            }
        });
    }

    @Override
    public void blinkText() {
        anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(50); //You can manage the blinking time with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        txtTimer.setAnimation(anim);
    }

    public void getUniqueRandomNumbers()
    {
        questionSequence = new ArrayList<>();
        for (int i=0; i<queList.size(); i++) {
            questionSequence.add(new Integer(i));
        }
        Collections.shuffle(questionSequence);
        for (int i=0; i<queList.size(); i++) {
            System.out.println(questionSequence.get(i));
        }
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
        if(item.getItemId() == R.id.mnuStop)
        {
            exitQuiz();
        }
        else if(item.getItemId() == R.id.logout)
        {
            presenter.logout();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }



    }
}