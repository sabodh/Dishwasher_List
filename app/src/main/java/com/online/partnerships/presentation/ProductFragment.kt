package com.online.partnerships.presentation

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.online.partnerships.R
import com.online.partnerships.adapter.SliderAdapter
import com.online.partnerships.databinding.FragmentProductBinding
import com.online.partnerships.model.data.ProductDetailsData
import com.online.partnerships.presentation.viewmodel.ProductViewModel
import com.online.partnerships.model.repository.DefaultRepositoryFactory
import com.online.partnerships.presentation.viewmodel.DefaultViewModelFactory
import com.online.partnerships.utils.*
import com.online.partnerships.utils.Constants.BUNDLE_PRODUCT_ID
import com.online.partnerships.utils.Constants.BUNDLE_PRODUCT_PRICE
import com.online.partnerships.utils.Constants.BUNDLE_PRODUCT_PRICE_DEFAULT

class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null
    val binding get() = _binding

    private var productId: String? = null
    private var productPrice: String? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        productId = arguments?.getString(BUNDLE_PRODUCT_ID, null)
        productPrice = arguments?.getString(BUNDLE_PRODUCT_PRICE, BUNDLE_PRODUCT_PRICE_DEFAULT)
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // checking network connection
        if (!NetworkUtils.isNetworkConnected(requireContext())) {
            binding?.progressBar?.let {
                showProgressVisibility(View.GONE)
                showSnackbar(getString(R.string.network_error))
                requireActivity().supportFragmentManager.popBackStack()
            }
            return
        }
        // creating product view model
        val productRepository = DefaultRepositoryFactory().createProductRepository()
        val viewModel = ViewModelProvider(this,
            DefaultViewModelFactory(productRepository)
        )
            .get(ProductViewModel::class.java)
        // only make network call if received a valid product id from initial page.
        productId?.let { viewModel.getProductsDetails(it) }
        viewModel.productDetails.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    val product = it.data as ProductDetailsData
                    showProductDetails(product)
                    // set selected product name in header
                    (activity as? ProductActivity)?.setHeader(
                        product.title!!,
                        Gravity.START,
                        View.VISIBLE
                    )
                    showProgressVisibility(View.GONE)
                }
                Status.ERROR -> {
                    showSnackbar(it.message.toString())
                    showProgressVisibility(View.GONE)
                }
                Status.LOADING -> {
                    showProgressVisibility(View.VISIBLE)
                }
            }
        })
    }

    /**
     * Method for populate details in the page
     */

    private fun showProductDetails(product: ProductDetailsData)    {

            binding?.txtAmount!!.text = productPrice
            binding?.txtInfo!!.text = product.displaySpecialOffer
            binding?.txtWarranty!!.text =
                product.additionalServices?.includedServices?.get(0) ?: ""
            binding?.txtSummary!!.text =
                StringUtils.getFormattedText(product.details?.productInformation!!)
            binding?.txtProductCode!!.text =
                getString(R.string.product_code).plus(": ").plus(product.code)
            /* data type provided as 'Any' because of it can retrieve as array and a single text.
               default is string but retrieve JsonArray [] also.
            */
            val response = when (product.dynamicAttributes?.adjustable) {
                is ArrayList<*> -> {
                    getString(R.string.yes)
                }
                is String -> getString(R.string.yes)
                else -> null
            }
            binding?.txtRackingInfoValue!!.text = response
            binding?.txtChildLockValue!!.text =
                StringUtils.convertToTitleCase(product.dynamicAttributes?.childlock!!)
            if (product.dynamicAttributes?.timerdelay.isNullOrEmpty()
                || product.dynamicAttributes?.timerdelay?.contains(
                    getString(R.string.no),
                    ignoreCase = true
                ) == true
            ) {
                binding?.txtDelayWashValue!!.text = getString(R.string.no)
            } else {
                binding?.txtDelayWashValue!!.text = getString(R.string.yes)
            }
            if (!product.dynamicAttributes?.delicatewash.isNullOrEmpty()) {
                binding?.txtDelicateWashValue!!.text =
                    StringUtils.convertToTitleCase(product.dynamicAttributes?.delicatewash!!)
            } else {
                binding?.txtDelicateWashValue!!.text = getString(R.string.no)
            }
            if (!product.dynamicAttributes?.dimensions.isNullOrEmpty()) {
                binding?.txtDimensionsValue!!.text =
                    StringUtils.convertToTitleCase(product.dynamicAttributes?.dimensions!!)
            } else {
                binding?.txtDimensionsValue!!.text = getString(R.string.no)
            }

            if (!product.dynamicAttributes?.dryingperformance.isNullOrEmpty()) {
                binding?.txtDryingPerformanceValue!!.text =
                    product.dynamicAttributes?.dryingperformance
            } else {
                binding?.txtDryingPerformanceValue!!.text = getString(R.string.no)
            }
            if (!product.dynamicAttributes?.dryingsystem.isNullOrEmpty()) {
                binding?.txtDryingSystemValue!!.text = product.dynamicAttributes?.dryingsystem
            } else {
                binding?.txtDryingSystemValue!!.text = getString(R.string.no)
            }
            val imageUrl = StringUtils.getMediaURLs(product.media?.images?.urls!!)
            binding?.imageSlider?.adapter = SliderAdapter(imageUrl)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showSnackbar(message: String) {
        SnackbarUtils.createSnackbar(binding!!.progressBar, message).show()
    }

    private fun showProgressVisibility(visibility: Int) {
        binding?.progressBar?.visibility = visibility

    }
}