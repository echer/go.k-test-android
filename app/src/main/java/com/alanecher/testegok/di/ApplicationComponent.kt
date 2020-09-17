package com.alanecher.testegok.di

import android.app.Application
import com.alanecher.testegok.ui.DigioApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,AppModule::class,ActivityBuilder::class,RetrofitModule::class])
interface ApplicationComponent : AndroidInjector<DaggerApplication> {
    fun inject(app: DigioApplication)
    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }
}