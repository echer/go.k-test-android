package com.alanecher.testegok.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alanecher.testegok.R
import com.alanecher.testegok.ui.BaseActivity
import com.alanecher.testegok.ui.DigioApplication
import dagger.android.support.DaggerAppCompatActivity
import okhttp3.OkHttpClient
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var okHttpClient: OkHttpClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}