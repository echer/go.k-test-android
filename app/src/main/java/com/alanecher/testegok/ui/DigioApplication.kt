package com.alanecher.testegok.ui

import android.app.Application
import dagger.Component

@Component
interface ApplicationComponent {

}

class DigioApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}