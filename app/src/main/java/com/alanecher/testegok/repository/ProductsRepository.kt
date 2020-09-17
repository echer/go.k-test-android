package com.alanecher.testegok.repository

import androidx.lifecycle.LiveData
import com.alanecher.testegok.repository.domain.Resource
import com.alanecher.testegok.repository.domain.dto.ProductsDTO

interface ProductsRepository {
    fun listProducts(): LiveData<Resource<ProductsDTO>>
}