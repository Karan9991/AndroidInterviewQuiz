package com.test.quizexampleinterview;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class QuizInstructionActivity extends AppCompatActivity
{
Button bk;

    WebView mWebViewInstruction;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_instruction);

        intiActionBar();

        mWebViewInstruction = (WebView)findViewById(R.id.webViewInstruction);
        mWebViewInstruction.loadUrl("file:///android_asset/quizinstruction.html");
        bk=(Button)findViewById(R.id.btnInstruction);
       bk.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getApplicationContext(),QuizDeskBoardActivity.class);
               startActivity(intent);
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
}
