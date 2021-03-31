package com.test.quizexampleinterview

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.test.quizexampleinterview.QuizMainActivity
import com.test.quizexampleinterview.database.DbHelper.Companion.getInstance
import com.test.quizexampleinterview.model.Questions
import java.util.*

class QuizMainActivity : AppCompatActivity(), Contract.View {
    var btnNext: Button? = null
    var txtTimer: TextView? = null
    var txtQue: TextView? = null
    var mCountDownTimer: CountDownTimer? = null
    var queList: List<Questions>? = null
    var anim: Animation? = null
    var questionSequence: ArrayList<Int>? = null
    var mRadioGroup: RadioGroup? = null
    var rbOption1: RadioButton? = null
    var rbOption2: RadioButton? = null
    var rbOption3: RadioButton? = null
    var rbOption4: RadioButton? = null
    var mProgressDialog: ProgressDialog? = null
    var mProgressBarQuiz: ProgressBar? = null
    var txtQno: TextView? = null
    var txtQueTitle: TextView? = null
    var currentQuestion: Questions? = null
    private var presenter: Presenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = Presenter(this, this)
        currentQue = 0
        initUI()
        initActionBar()
        initTimer()
        setUpQuizQuestions()
        blinkText()
        uniqueRandomNumbers
        nextQuestion()
    }

    override fun initTimer() {
        mCountDownTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val sec = millisUntilFinished / 1000
                txtTimer!!.text = "$sec Sec"
                txtTimer!!.setTextColor(Color.BLACK)
                if (sec <= 5) {
                    txtTimer!!.setTextColor(Color.RED)
                    anim!!.repeatCount = Animation.INFINITE
                    blinkText()
                } else {
                    anim!!.repeatCount = 0
                }
            }

            override fun onFinish() {
                txtTimer!!.text = "Done!"
                nextQuestion()
            }
        }
    }

    private fun setUpQuizQuestions() {
        queList = getInstance(this)!!.allQuestions
    }

    private val onButtonClick = View.OnClickListener { v ->
        when (v.id) {
            R.id.btnNext -> {
                mCountDownTimer!!.cancel()
                mProgressDialog!!.show()
                val timerThread: Thread = object : Thread() {
                    override fun run() {
                        try {
                            sleep(3000)
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        } finally {
                            runOnUiThread {
                                mProgressDialog!!.hide()
                                nextQuestion()
                            }
                        }
                    }
                }
                timerThread.start()
            }
            else -> Toast.makeText(applicationContext, (v as Button).text.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun exitQuiz() {
        currentQue = 0
        mCountDownTimer!!.cancel()
        val b = AlertDialog.Builder(this@QuizMainActivity)
                .setTitle("Exit")
                .setPositiveButton("Ok") { dialog, which ->
                    dialog.dismiss()
                    finish()
                    sendResult()
                }
                .setMessage("You will end the quiz!!!")
        b.create().show()
    }

    private fun nextQuestion(){
      //  private get() {
            if (currentQue < queList!!.size) {
                val c = questionSequence!![currentQue]
                currentQue++
                mProgressBarQuiz!!.progress = currentQue
                txtQno!!.text = currentQue.toString() + "/" + queList!!.size
                currentQuestion = queList!![c]
                txtQueTitle!!.text = "[ Q : " + currentQue + " ] "
                txtQue!!.text = currentQuestion!!.qUESTION
                mRadioGroup!!.clearCheck()
                rbOption1!!.text = currentQuestion!!.oPTA
                rbOption2!!.text = currentQuestion!!.oPTB
                rbOption3!!.text = currentQuestion!!.oPTC
                rbOption4!!.text = currentQuestion!!.oPTD
                mCountDownTimer!!.start()
            } else {
                mProgressDialog!!.setMessage("Cool!!! Preparing results....")
                mProgressDialog!!.show()
                val timerThread: Thread = object : Thread() {
                    override fun run() {
                        try {
                            sleep(3000)
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        } finally {
                            runOnUiThread {
                                mProgressDialog!!.hide()
                                sendResult()
                            }
                        }
                    }
                }
                timerThread.start()
            }
        }

    private fun sendResult() {
        val intent = Intent(applicationContext, QuizResultActivity::class.java)
        intent.putExtra("result", score)
        startActivity(intent)
    }

    override fun initActionBar() {
        val actionBar = supportActionBar
        actionBar!!.setLogo(R.drawable.trophyyy)
        actionBar.setDisplayUseLogoEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)
    }

    override fun initUI() {
        mProgressDialog = ProgressDialog(this)
        mProgressDialog!!.setMessage("Wait!!! Question Loading...")
        mProgressDialog!!.setCancelable(false)
        mProgressBarQuiz = findViewById<View>(R.id.progressQuiz) as ProgressBar
        txtQno = findViewById<View>(R.id.txtQno) as TextView
        txtTimer = findViewById<View>(R.id.txtTimer) as TextView
        txtQue = findViewById<View>(R.id.txtQue) as TextView
        txtQueTitle = findViewById<View>(R.id.txtQueTitle) as TextView
        txtTimer!!.text = "00 Sec"
        btnNext = findViewById<View>(R.id.btnNext) as Button
        btnNext!!.setOnClickListener(onButtonClick)
        mRadioGroup = findViewById<View>(R.id.rbGroup) as RadioGroup
        rbOption1 = findViewById<View>(R.id.rbOption1) as RadioButton
        rbOption2 = findViewById<View>(R.id.rbOption2) as RadioButton
        rbOption3 = findViewById<View>(R.id.rbOption3) as RadioButton
        rbOption4 = findViewById<View>(R.id.rbOption4) as RadioButton
        mRadioGroup!!.setOnCheckedChangeListener { group, checkedId ->
            var ans = ""
            when (checkedId) {
                R.id.rbOption1 -> ans = rbOption1!!.text.toString()
                R.id.rbOption2 -> ans = rbOption2!!.text.toString()
                R.id.rbOption3 -> ans = rbOption3!!.text.toString()
                R.id.rbOption4 -> ans = rbOption4!!.text.toString()
            }
            if (currentQuestion!!.aNSWER.equals(ans, ignoreCase = true)) {
                score++
            }
            Log.d("RES", "onCheckedChanged: " + ans + "[" + score + "]")
        }
    }

    override fun blinkText() {
        anim = AlphaAnimation(0.0f, 1.0f)
        anim!!.setDuration(50) //You can manage the blinking time with this parameter
        anim!!.setStartOffset(20)
        anim!!.setRepeatMode(Animation.REVERSE)
        anim!!.setRepeatCount(Animation.INFINITE)
        txtTimer!!.animation = anim
    }

    val uniqueRandomNumbers: Unit
        get() {
            questionSequence = ArrayList()
            for (i in queList!!.indices) {
                questionSequence!!.add(i)
            }
            Collections.shuffle(questionSequence)
            for (i in queList!!.indices) {
                println(questionSequence!![i])
            }
        }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.mymenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mnuStop) {
            exitQuiz()
        } else if (item.itemId == R.id.logout) {
            presenter!!.logout()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show()
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        var currentQue = 0
        var score = 0
    }
}