package com.test.quizexampleinterview

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class QuizDeskBoardActivity : AppCompatActivity(), View.OnClickListener, Contract.View {
    private var btnStart: Button? = null
    private var btnInstruction: Button? = null
    private var presenter: Presenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_desk_board)
        presenter = Presenter(this, this)
        initActionBar()
        initUI()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnStart -> presenter!!.alertDialog("Start Quiz", "All the Best!!!", "Ok", "Cancel", this@QuizDeskBoardActivity, QuizMainActivity::class.java)
            R.id.btnInstruction -> presenter!!.navigateToAhead(QuizInstructionActivity::class.java)
        }
    }

    override fun initActionBar() {
        val actionBar = supportActionBar
        actionBar!!.setLogo(R.drawable.trophyyy)
        actionBar.setDisplayUseLogoEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)
    }

    override fun initUI() {
        btnStart = findViewById<View>(R.id.btnStart) as Button
        btnInstruction = findViewById<View>(R.id.btnInstruction) as Button
        btnStart!!.setOnClickListener(this)
        btnInstruction!!.setOnClickListener(this)
    }
}