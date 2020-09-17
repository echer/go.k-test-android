package com.alanecher.testegok.di

import com.alanecher.testegok.ui.main.MainActivity
import com.alanecher.testegok.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector
    abstract fun provideMainActivity(): MainActivity
    @ContributesAndroidInjector
    abstract fun provideSplashActivity(): SplashActivity
}