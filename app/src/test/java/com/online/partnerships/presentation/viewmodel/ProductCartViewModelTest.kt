package com.online.partnerships.presentation.viewmodel

import com.online.partnerships.model.data.ProductCartItem
import com.online.partnerships.model.getOrAwaitValue

import org.junit.After
import org.junit.Assert
import org.junit.Before

class ProductCartViewModelTest {


    @Before
    fun setUp() {
    }

    fun test_addProducts(){

        val product = ProductCartItem(productId = "123",
        productName = "Bosch", productQuantity = 1, productPrice = 100.00)
        val viewModel = ProductCartViewModel()
        viewModel.addProductToList(product = product)
        val result  = viewModel.cartItems.getOrAwaitValue()
        Assert.assertNotNull(result)
        Assert.assertEquals(product.productId, result.get(0).productId)


    }

    @After
    fun tearDown() {
    }
}