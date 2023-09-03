package com.online.partnerships.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.online.partnerships.model.data.ProductDetailsData
import com.online.partnerships.model.data.ProductLists
import com.online.partnerships.model.repository.DefaultProductRepository
import com.online.partnerships.utils.Responses
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(
    private val productRepository: DefaultProductRepository,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
    // Data holder for products
    private val _products = MutableLiveData<Responses<ProductLists>>()
    val products: LiveData<Responses<ProductLists>>
        get() = _products

    // Data holder for Product details
    private val _productDetails = MutableLiveData<Responses<ProductDetailsData>>()
    val productDetails: LiveData<Responses<ProductDetailsData>>
        get() = _productDetails





    /**
     * Get the list of Products based on the input value.
     */
    fun getProducts(product: String) {
        _products.postValue(Responses.loading(null))
        viewModelScope.launch(coroutineDispatcher) {
            val result = productRepository.getProductList(product)
            _products.postValue(result)
        }
    }

    /**
     * Get the details of a particular product based on productId.
     */
    fun getProductsDetails(productId: String) {
        _productDetails.postValue(Responses.loading(null))
        viewModelScope.launch(coroutineDispatcher) {
            val result = productRepository.getProductDetails(productId)
            _productDetails.postValue(result)
        }
    }

}