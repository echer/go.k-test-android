package com.alanecher.testegok.repository

import com.alanecher.testegok.repository.domain.dto.ProductsDTO

interface ProductsRepository {
    suspend fun listProducst(): ProductsDTO
}