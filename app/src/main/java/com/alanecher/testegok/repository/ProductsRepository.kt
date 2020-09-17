package com.alanecher.testegok.repository

import com.alanecher.testegok.repository.domain.dto.ProductsDTO
import com.alanecher.testegok.repository.remote.BaseResponse

interface ProductsRepository {
    fun listProducts(): BaseResponse<ProductsDTO>
}