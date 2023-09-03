package com.online.partnerships.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.online.partnerships.R
import com.online.partnerships.adapter.ProductAdapter
import com.online.partnerships.databinding.ActivityMainBinding
import com.online.partnerships.databinding.ToolbarLayoutBinding
import com.online.partnerships.model.data.Products
import com.online.partnerships.presentation.viewmodel.ProductViewModel
import com.online.partnerships.model.repository.DefaultRepositoryFactory
import com.online.partnerships.presentation.viewmodel.DefaultViewModelFactory
import com.online.partnerships.utils.*
import com.online.partnerships.utils.Constants.DISH_WASHER

class ProductActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    val binding  get() = _binding
    private var actionbarBinding: ToolbarLayoutBinding? = null
    private var SPAN_COUNT: Int = 2
    private var dishWasherCount: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        // showing actionbar
        setCustomActionbar()

        // creating Product viewModel
        val productRepository = DefaultRepositoryFactory().createProductRepository()
        val viewModel = ViewModelProvider(this,
            DefaultViewModelFactory(productRepository)
        )
            .get(ProductViewModel::class.java)
        binding?.swipeRefreshLayout?.setOnRefreshListener {
            showPullToRefresh(true)
            viewModel.getProducts(DISH_WASHER)
        }
        viewModel.products.observe(this, Observer { it ->
            when (it.status) {
                Status.SUCCESS -> {
                    listProducts(it.data!!.products)
                    showProgressVisibility(View.GONE)
                    showPullToRefresh(false)
                }
                Status.ERROR -> {
                    dishWasherCount = 0
                    showProgressVisibility(View.GONE)
                    showPullToRefresh(false)
                    showSnackbar(it.message!!)
                }
                Status.LOADING -> {
                    showProgressVisibility(View.VISIBLE)
                }
            }
            // show the total dishwasher on the actionbar based on the api list.
            if (supportFragmentManager.backStackEntryCount == 0)
                setHeader(
                    getString(R.string.dish_washers, dishWasherCount),
                    Gravity.CENTER,
                    View.GONE
                )
        })
        // checking network connectivity
        if (!NetworkUtils.isNetworkConnected(this)) {
            binding?.progressBar?.let {
                showSnackbar(getString(R.string.network_error))
                showProgressVisibility(View.GONE)
            }
            return
        }
        // invoking network call for listing products
        viewModel.getProducts(DISH_WASHER)
    }


    /**
     * Method used to populate the product list
     */
    private fun listProducts(products: ArrayList<Products>) {
        // Getting the product grid visibility cout based on screen resolution
        SPAN_COUNT = ScreenUtils.getGridCount(this)
        val adapter = ProductAdapter(::onProductItemClick)
        binding?.recyclerView?.layoutManager = GridLayoutManager(this, SPAN_COUNT)
        binding?.recyclerView?.adapter = adapter
        binding?.recyclerView?.visibility = View.VISIBLE
        dishWasherCount = products.size
        adapter.submitList(products)
    }

    fun setHeader(header: String, gravity: Int, visibility: Int) {
        actionbarBinding?.actionBarTitle?.text = header
        handleActionbar(gravity, visibility)
    }

    /**
     * Handle actionbar menu based on screen change
     */
    private fun handleActionbar(gravity: Int, visibility: Int) {
        actionbarBinding?.let {
            it.actionBarTitle.gravity = gravity
            it.backMenu.visibility = visibility
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showSnackbar(message: String) {
        SnackbarUtils.createSnackbar(binding!!.recyclerView, message).show()
    }

    private fun showProgressVisibility(visibility: Int) {
        binding?.progressBar?.visibility = visibility

    }

    private fun showPullToRefresh(visibility: Boolean) {
        binding?.swipeRefreshLayout?.isRefreshing = visibility
    }

    /**
     * Handling actionbar configurations
     */
    private fun setCustomActionbar() {
        actionbarBinding = ToolbarLayoutBinding.inflate(layoutInflater)
        actionbarBinding?.backMenu?.visibility = View.GONE
        actionbarBinding?.backMenu?.setOnClickListener {
            supportFragmentManager.popBackStack()
            setHeader(getString(R.string.dish_washers, dishWasherCount), Gravity.CENTER, View.GONE)
        }
        supportActionBar?.apply {
            displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            setBackgroundDrawable(AppCompatResources.getDrawable(baseContext, R.color.white))
            setCustomView(actionbarBinding?.root)
        }
    }
    private fun onProductItemClick(product: Products){
        navigateToDetails(product)
    }
    private fun navigateToDetails(product: Products) {
        val fragment = ProductFragment()
        val bundle = Bundle()
        bundle.putString(Constants.BUNDLE_PRODUCT_ID, product.productId)
        bundle.putString(Constants.BUNDLE_PRODUCT_PRICE, product.variantPriceRange?.display?.min)
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.rootView, fragment)
            .addToBackStack(Constants.FRAGMENT_TAG)
            .commit()
    }

}