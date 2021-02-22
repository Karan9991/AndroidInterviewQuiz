package com.test.quizexampleinterview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class QuizInstructionActivity extends AppCompatActivity implements Contract.View {

    private Button bk;
    private WebView mWebViewInstruction;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_instruction);

        presenter = new Presenter(this, this);

        initActionBar();
        initUI();

        bk.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
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
        mWebViewInstruction = findViewById(R.id.webViewInstruction);
        mWebViewInstruction.loadUrl("file:///android_asset/quizinstruction.html");
        bk = findViewById(R.id.btnInstruction);
    }

}
