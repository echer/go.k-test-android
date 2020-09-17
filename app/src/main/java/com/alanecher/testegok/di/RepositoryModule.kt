package com.alanecher.testegok.di

import com.alanecher.testegok.repository.remote.ProductsAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideProductsAPI(
        retrofit: Retrofit
    ): ProductsAPI {
        return retrofit.create(ProductsAPI::class.java)
    }
}