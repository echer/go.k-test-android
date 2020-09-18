package com.alanecher.testegok.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alanecher.testegok.repository.ProductsRepository
import com.alanecher.testegok.repository.domain.dto.ProductsDTO
import com.alanecher.testegok.repository.remote.BaseResponse
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel @ViewModelInject constructor(
    private val repository: ProductsRepository,
    private val dispatcherProvider: DispatcherProvider = DefaultDispatcherProvider()
) :
    ViewModel() {
    val loading = MutableLiveData<Boolean>()
    val products = MutableLiveData<ProductsDTO>()
    val error = MutableLiveData<String>()

    fun listProducts() {
        loading.value = true
        viewModelScope.launch {
            withContext(dispatcherProvider.default()) {
                val result = repository.listProducts()

                launch(dispatcherProvider.main()) {
                    if (result is BaseResponse.Success)
                        products.value = result.data
                    if (result is BaseResponse.Error)
                        error.value = result.message
                    loading.value = false
                }
            }
        }
    }

}