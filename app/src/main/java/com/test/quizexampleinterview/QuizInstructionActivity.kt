package com.test.quizexampleinterview

import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class QuizInstructionActivity : AppCompatActivity(), Contract.View {
    private var bk: Button? = null
    private var mWebViewInstruction: WebView? = null
    private var presenter: Presenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_instruction)
        presenter = Presenter(this, this)
        initActionBar()
        initUI()
        bk!!.setOnClickListener { presenter!!.navigateToAhead(QuizDeskBoardActivity::class.java) }
    }

    override fun initActionBar() {
        val actionBar = supportActionBar
        actionBar!!.setLogo(R.drawable.trophyyy)
        actionBar.setDisplayUseLogoEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)
    }
//changes
    override fun initUI() {
        mWebViewInstruction = findViewById(R.id.webViewInstruction)
        mWebViewInstruction!!.loadUrl("file:///android_asset/quizinstruction.html")
        bk = findViewById(R.id.btnInstruction)
    }
}