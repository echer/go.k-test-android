package com.alanecher.testegok.repository

import com.alanecher.testegok.repository.BaseRepository.handleError
import com.alanecher.testegok.repository.BaseRepository.handleSuccess
import com.alanecher.testegok.repository.domain.dto.ProductsDTO
import com.alanecher.testegok.repository.remote.BaseResponse
import com.alanecher.testegok.repository.remote.ProductsAPI
import retrofit2.Response

class ProductsRepositoryImpl(
    private val productsAPI: ProductsAPI
) : ProductsRepository {

    override fun listProducts(): BaseResponse<ProductsDTO> {
        val call = productsAPI.listProducts()
        val response: Response<ProductsDTO> = call.execute()
        return if (response != null && response.isSuccessful)
            handleSuccess(response)
        else
            handleError(response)
    }

}