package com.alanecher.testegok.ui.main

import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alanecher.testegok.repository.ProductsRepository
import com.alanecher.testegok.repository.domain.Resource
import com.alanecher.testegok.repository.domain.dto.ProductsDTO

class MainViewModel @ViewModelInject constructor(repository: ProductsRepository) : ViewModel() {
    val loading = ObservableBoolean()
    val error = ObservableBoolean()
    val products: LiveData<Resource<ProductsDTO>> = repository.listProducts()

}