package com.alanecher.testegok.di

import com.alanecher.testegok.repository.ProductsRepository
import com.alanecher.testegok.ui.main.DefaultDispatcherProvider
import com.alanecher.testegok.ui.main.DispatcherProvider
import com.alanecher.testegok.ui.main.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class ViewModelModule {

    @Provides
    fun provideMainViewModel(
        repository: ProductsRepository
    ): MainViewModel {
        return MainViewModel(repository)
    }

    @Provides
    fun provideDispatcher(): DispatcherProvider {
        return DefaultDispatcherProvider()
    }
}