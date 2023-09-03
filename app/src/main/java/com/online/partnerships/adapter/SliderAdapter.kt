package com.online.partnerships.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.online.partnerships.databinding.SliderImageBinding
import com.online.partnerships.model.data.SliderItem

class SliderAdapter(private val items: List<SliderItem>) :
    RecyclerView.Adapter<SliderAdapter.ImageViewHolder>() {

    class ImageViewHolder(private val binding: SliderImageBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bindingItem(context: Context, imageUrl: String){
                Glide.with(context).
                load(imageUrl).into(binding.imageView)
            }

        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(SliderImageBinding
            .inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = items[position]
        holder.bindingItem(holder.itemView.context, item.imageUrl)
    }

    override fun getItemCount() = items.size


}
