package com.test.quizexampleinterview

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.test.quizexampleinterview.authentication.SignIn

class SplashScreenActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState)
        //Remove title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        //Remove notification bar
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash_screen)
        //getActionBar().hide();
        val timerThread: Thread = object : Thread() {
            override fun run() {
                try {
                    sleep(3000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    val intent = Intent(this@SplashScreenActivity, SignIn::class.java)
                    startActivity(intent)
                }
            }
        }
        timerThread.start()
    }

    override fun onPause() {
        // TODO Auto-generated method stub
        super.onPause()
        finish()
    }
}