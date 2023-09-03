package com.online.partnerships.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.online.partnerships.model.data.ProductCartItem

class ProductCartViewModel:ViewModel() {

    private val _cartItems = MutableLiveData<MutableList<ProductCartItem>> ()
    val cartItems get() = _cartItems

    fun addProductToList(product: ProductCartItem){
        _cartItems.value?.add(product)
    }

}