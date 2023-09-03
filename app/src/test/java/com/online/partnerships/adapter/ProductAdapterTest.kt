package com.online.partnerships.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View

import androidx.cardview.widget.CardView
import androidx.test.core.app.ApplicationProvider
import com.online.partnerships.Helper
import com.online.partnerships.model.data.ProductLists
import com.online.partnerships.model.data.Products
import com.online.partnerships.databinding.RowProductBinding
import com.online.partnerships.utils.StringUtils
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ProductAdapterTest {

    private lateinit var mockItems: ArrayList<Products>
    private var context: Context = ApplicationProvider.getApplicationContext()

    @Before
    fun setUp() {
        val content = Helper.readFileResource("/data.json")
        val jsonData = Helper.fromJson(content, ProductLists::class.java)
        mockItems = jsonData!!.products
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testOnBindViewHolder() {
        val adapter = ProductAdapter(mockItems)
        val mockViewHolder = mock(ProductAdapter.ProductViewHolder::class.java)
        adapter.onBindViewHolder(mockViewHolder, 0)
        // Verify mockViewHolder called with proper parameters
        verify(mockViewHolder).bindItem(mockItems[0])
    }

    @Test
    fun test_onCreateViewHolder_inflates_correct_layout() {

        val mockView = mock(CardView::class.java)
        val mockBinding = mock(RowProductBinding::class.java)
        `when`(mockBinding.root).thenReturn(mockView)
        val viewHolder = ProductAdapter.ProductViewHolder(mockBinding)
        assertNotNull(viewHolder)
        assertNotNull(viewHolder.binding)
    }

    @Test
    fun test_onBindViewHolder_bind_product_to_view_holder() {
        // used actual object rather than mock
        val product = mockItems.get(0)
        val mockViewHolder = mock(ProductAdapter.ProductViewHolder::class.java)
        val formattedUrl = StringUtils.getFormattedURL(product.image)
        val adapter = ProductAdapter(mockItems)
        adapter.onBindViewHolder(mockViewHolder, 0)
        verify(mockViewHolder).bindItem(product)
        verify(mockViewHolder).bindImage(formattedUrl)
    }

    @Test
    fun test_getItemCount_should_return_the_count() {
        val expectedCount = 3
        val mockProducts = mock(ArrayList::class.java) as ArrayList<Products>
        `when`(mockProducts.size).thenReturn(expectedCount)
        val adapter = ProductAdapter(mockProducts)
        val result = adapter.itemCount
        assertEquals(expectedCount, result)
    }

    @Test
    fun test_values_render_properly(){
        val bindingValue =
            RowProductBinding.inflate(
                LayoutInflater.from(context),
                CardView(context), false)
        val viewHolder = ProductAdapter.ProductViewHolder(bindingValue)
        val adapter = ProductAdapter(arrayListOf(
            Products(title = "Bosch Serie 2 SMS24AW01G Freestanding Dishwasher, White",
            productId = "3218074"),
            Products(title = "Bosch Serie 2 SMV40C30GB Fully Integrated Dishwasher",
                productId = "1955287")
        ))
        adapter.onBindViewHolder(viewHolder, 0)
        Assert.assertEquals("Bosch Serie 2 SMS24AW01G Freestanding Dishwasher, White",
            viewHolder.binding.txtProductName.text)

    }
    @Test
    fun test_item_click_event(){
        val bindingValue =
            RowProductBinding.inflate(
                LayoutInflater.from(context),
                CardView(context), false)
        val viewHolder = ProductAdapter.ProductViewHolder(bindingValue)
        val mockListener = mock(View.OnClickListener::class.java)
        viewHolder.binding.touchCard.setOnClickListener(mockListener)
        viewHolder.binding.touchCard.performClick()
        // Verify the click is performed properly
        verify(mockListener).onClick(viewHolder.binding.touchCard)
    }
    @Test
    fun test_click_operations(){
        val bindingValue =
            RowProductBinding.inflate(
                LayoutInflater.from(context),
                CardView(context), false)
        val viewHolder = ProductAdapter.ProductViewHolder(bindingValue)
        val adapter = ProductAdapter(arrayListOf(
            Products(title = "Bosch Serie 2 SMS24AW01G Freestanding Dishwasher, White",
                productId = "3218074"),
            Products(title = "Bosch Serie 2 SMV40C30GB Fully Integrated Dishwasher",
                productId = "1955287")
        ))
        adapter.onBindViewHolder(viewHolder, 1)
        Assert.assertEquals("Bosch Serie 2 SMV40C30GB Fully Integrated Dishwasher",
            viewHolder.binding.txtProductName.text)
        //verify(viewHolder).navigateToDetails(context, "1955287", "")


    }
}