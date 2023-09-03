package com.online.partnerships.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.online.partnerships.R
import com.online.partnerships.databinding.RowProductBinding
import com.online.partnerships.model.data.Products
import com.online.partnerships.presentation.ProductFragment
import com.online.partnerships.utils.StringUtils
import com.online.partnerships.utils.Constants.BUNDLE_PRODUCT_ID
import com.online.partnerships.utils.Constants.BUNDLE_PRODUCT_PRICE
import com.online.partnerships.utils.Constants.FRAGMENT_TAG

class ProductAdapter(var products: ArrayList<Products>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(val binding: RowProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(product: Products) {
            binding.txtProductName.text = product.title
            binding.txtPrice.text = product.variantPriceRange?.display?.min
            binding.touchCard.setOnClickListener {
                val price = product.variantPriceRange?.display?.min
                val productId = product.productId
                navigateToDetails(it.context, productId, price)
            }
        }

        fun bindImage(imageUrl: String) {
            Glide.with(binding.image)
                .load(imageUrl)
                .into(binding.image)
        }

        fun navigateToDetails(context: Context, productId: String?, productPrice: String?) {
            val activity = context as AppCompatActivity
            val fragment = ProductFragment()
            val bundle = Bundle()
            bundle.putString(BUNDLE_PRODUCT_ID, productId)
            bundle.putString(BUNDLE_PRODUCT_PRICE, productPrice)
            fragment.arguments = bundle
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.rootView, fragment)
                .addToBackStack(FRAGMENT_TAG)
                .commit()
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        return ProductViewHolder(
            RowProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.bindItem(product)
        holder.bindImage(StringUtils.getFormattedURL(product.image))
    }

    override fun getItemCount(): Int {
        return products.size
    }

}