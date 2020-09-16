package com.alanecher.testegok.repository.remote

import com.alanecher.testegok.repository.domain.dto.ProductsDTO
import retrofit2.Response
import retrofit2.http.GET

const val ENDPOINT = "https://7hgi9vtkdc.execute-api.sa-east-1.amazonaws.com/sandbox/"
const val PRODUCTS = "${ENDPOINT}products/"

interface ProductsAPI {

    @GET(PRODUCTS)
    suspend fun listProducts(): Response<ProductsDTO>

}