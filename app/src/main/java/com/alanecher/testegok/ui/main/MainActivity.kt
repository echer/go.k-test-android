package com.alanecher.testegok.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alanecher.testegok.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, MainFragment.newInstance())
                .commitNow()
        }
    }
}