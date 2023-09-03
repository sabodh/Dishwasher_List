package com.online.partnerships.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.cardview.widget.CardView
import androidx.test.core.app.ApplicationProvider
import com.online.partnerships.Helper
import com.online.partnerships.databinding.RowProductBinding
import com.online.partnerships.model.data.ProductLists
import com.online.partnerships.model.data.Products
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ProductAdapterViewTest {


    private lateinit var mockItems: ArrayList<Products>
    private var context: Context = ApplicationProvider.getApplicationContext()

    @Before
    fun setUp() {
        val content = Helper.readFileResource("/data.json")
        val jsonData = Helper.fromJson(content, ProductLists::class.java)
        mockItems = jsonData!!.products

    }

    @Test
    fun test_view_rendering_properly(){
       val bindingValue =
           RowProductBinding.inflate(LayoutInflater.from(context),
               CardView(context), false)
        val viewHolder = ProductAdapter.ProductViewHolder(bindingValue)
        Assert.assertNotNull(viewHolder)
        Assert.assertNotNull(viewHolder.binding)
        Assert.assertNotNull(viewHolder.binding.txtProductName)
    }

    @Test
    fun testOnBindViewHolder() {
        val mockView = mock(CardView::class.java)
        val mockBinding = mock(RowProductBinding::class.java)
        `when`(mockBinding.root).thenReturn(mockView)
        val viewHolder = ProductAdapter.ProductViewHolder(mockBinding)
        Assert.assertNotNull(viewHolder)
        Assert.assertNotNull(viewHolder.binding)

        doAnswer {
            val onClickListener = it.getArgument<View.OnClickListener>(0)
            onClickListener.onClick(mockView)
        }.`when`(mockView).setOnClickListener(any())
        Assert.assertNull(mockBinding.txtProductName)

    }
}