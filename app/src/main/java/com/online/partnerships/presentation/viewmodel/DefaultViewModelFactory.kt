package com.online.partnerships.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.online.partnerships.model.repository.DefaultProductRepository
import com.online.partnerships.model.repository.ProductRepository

class DefaultViewModelFactory(private val productRepository: ProductRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductViewModel(productRepository as DefaultProductRepository) as T
    }
}