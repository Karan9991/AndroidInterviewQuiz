package com.test.quizexampleinterview

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuizResultActivity : AppCompatActivity(), Contract.View {
    private var txtScore: TextView? = null
    private var txtResult: TextView? = null
    private var txtGreetings: TextView? = null
    private var edt: TextView? = null
    private var btnplagain: Button? = null
    private var presenter: Presenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_result)
        presenter = Presenter(this, this)
        initActionBar()
        initUI()
        btnplagain!!.setOnClickListener { presenter!!.navigateToAhead(QuizDeskBoardActivity::class.java) }
    }

    override fun initActionBar() {
        val actionBar = supportActionBar
        actionBar!!.setLogo(R.drawable.trophyyy)
        actionBar.setDisplayUseLogoEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)
    }

    override fun initUI() {
        txtScore = findViewById<View>(R.id.txtScore) as TextView
        txtResult = findViewById<View>(R.id.txtResult) as TextView
        txtGreetings = findViewById<View>(R.id.txtGreetings) as TextView
        edt = findViewById<View>(R.id.editText) as TextView
        btnplagain = findViewById<View>(R.id.playagainbtn) as Button
        val score = intent.extras.getInt("result", 0)
        txtScore!!.text = "Score : $score"
        blinkText()
        if (score < 5) {
            txtScore!!.setTextColor(Color.RED)
            txtResult!!.text = "Fail"
            txtGreetings!!.text = "Bad Luck!!!"
        } else {
            txtScore!!.setTextColor(Color.GREEN)
            txtResult!!.text = "Pass"
            txtGreetings!!.text = "Congratulation!!!"
        }
        val sharedPreferences = getPreferences(MODE_PRIVATE)
        val highscore = sharedPreferences.getInt("highscore", 0)
        if (highscore >= score) {
            edt!!.text = "Highest Score : $highscore"
        } else {
            edt!!.text = "New Score : $score"
            val editor = sharedPreferences.edit()
            editor.putInt("highscore", score)
            editor.commit()
        }
    }

    override fun blinkText() {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 50
        anim.startOffset = 20
        anim.repeatMode = Animation.REVERSE
        txtGreetings!!.animation = anim
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.resultmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout) {
            presenter!!.logout()
        }
        return super.onOptionsItemSelected(item)
    }
}