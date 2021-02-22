package com.test.quizexampleinterview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.test.quizexampleinterview.database.DbHelper;

public class QuizDeskBoardActivity extends AppCompatActivity implements View.OnClickListener, Contract.View
{

    private Button btnStart;
    private Button btnInstruction;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_desk_board);

        presenter = new Presenter(this, this);

        initActionBar();
        initUI();

        }


    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnStart:
                presenter.alertDialog("Start Quiz", "All the Best!!!", "Ok", "Cancel", QuizDeskBoardActivity.this, QuizMainActivity.class);
                break;

            case R.id.btnInstruction:
                presenter.navigateToAhead(QuizInstructionActivity.class);
                break;
        }


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
        btnStart = (Button)findViewById(R.id.btnStart);
        btnInstruction = (Button)findViewById(R.id.btnInstruction);
        btnStart.setOnClickListener(this);
        btnInstruction.setOnClickListener(this);
    }
}
