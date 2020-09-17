package com.alanecher.testegok.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.alanecher.testegok.repository.domain.Resource
import com.alanecher.testegok.repository.domain.dto.ProductsDTO
import com.alanecher.testegok.repository.remote.BaseRepository
import com.alanecher.testegok.repository.remote.ProductsAPI
import kotlinx.coroutines.Dispatchers

class ProducstRepositoryImpl(private val productsAPI: ProductsAPI) : BaseRepository(),
    ProductsRepository {

    override fun listProducts(): LiveData<Resource<ProductsDTO>> {
        return performGetOperation(suspend { getResult { productsAPI.listProducts() } })
    }

    private fun <T, A> performGetOperation(
        call: suspend () -> Resource<A>
    ): LiveData<Resource<T>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val responseStatus = call.invoke()
            if (responseStatus.status == Resource.Status.SUCCESS) {
                emitSource(liveData { emit(Resource.success((responseStatus.data!! as T))) })
            } else if (responseStatus.status == Resource.Status.ERROR) {
                emit(Resource.error(responseStatus.message!!))
            }
        }
}