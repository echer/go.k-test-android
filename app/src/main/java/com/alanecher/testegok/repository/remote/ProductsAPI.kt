package com.alanecher.testegok.repository.remote

import com.alanecher.testegok.di.baseURL
import com.alanecher.testegok.repository.domain.dto.ProductsDTO
import retrofit2.Response
import retrofit2.http.GET

const val PRODUCTS = "${baseURL}products/"

interface ProductsAPI {

    @GET(PRODUCTS)
    suspend fun listProducts(): Response<ProductsDTO>

}