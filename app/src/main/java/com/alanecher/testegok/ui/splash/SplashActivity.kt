package com.alanecher.testegok.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.alanecher.testegok.R
import com.alanecher.testegok.ui.BaseActivity
import com.alanecher.testegok.ui.main.MainActivity
import dagger.android.support.DaggerAppCompatActivity

class SplashActivity : BaseActivity() {

    private val SPLASH_TIMEOUT: Long = 1400

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
        }, SPLASH_TIMEOUT)
    }
}