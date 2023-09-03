package com.online.partnerships.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.online.partnerships.databinding.RowProductBinding
import com.online.partnerships.model.data.Products
import com.online.partnerships.utils.StringUtils

class ProductAdapter(private val onProductItemClick: (Products) -> Unit,):
    ListAdapter<Products, ProductAdapter.ProductViewHolder>(ComparatorDiffUtil()) {

    inner class ProductViewHolder(val binding: RowProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(product: Products) {
            binding.txtProductName.text = product.title
            binding.txtPrice.text = product.variantPriceRange?.display?.min
            binding.touchCard.setOnClickListener {
                onProductItemClick(product)
            }
        }

        fun bindImage(imageUrl: String) {
            Glide.with(binding.image)
                .load(imageUrl)
                .into(binding.image)
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
        val product = getItem(position)
        holder.bindItem(product)
        holder.bindImage(StringUtils.getFormattedURL(product.image))
    }


    class ComparatorDiffUtil :DiffUtil.ItemCallback<Products>() {
        override fun areItemsTheSame(oldItem: Products, newItem: Products): Boolean {
            return oldItem.productId == newItem.productId
        }

        override fun areContentsTheSame(oldItem: Products, newItem: Products): Boolean {
            return oldItem == newItem
        }
    }

}