package com.alanecher.testegok.di

import com.alanecher.testegok.repository.ProductsRepositoryImpl
import com.alanecher.testegok.repository.ProductsRepository
import com.alanecher.testegok.repository.remote.ProductsAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideProductsAPI(
        retrofit: Retrofit
    ): ProductsAPI {
        return retrofit.create(ProductsAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideProductsRepository(
        api: ProductsAPI
    ): ProductsRepository {
        return ProductsRepositoryImpl(api)
    }
}