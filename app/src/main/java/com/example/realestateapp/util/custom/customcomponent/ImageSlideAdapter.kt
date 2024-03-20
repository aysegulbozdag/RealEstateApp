package com.example.realestateapp.util.custom.customcomponent

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.realestateapp.R
import com.example.realestateapp.databinding.ViewpagerItemImageBinding

class ImageSlideAdapter(private val imageUrlList: List<String>) :
    RecyclerView.Adapter<ImageSlideAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(val binding: ViewpagerItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(imageUrl: String) {

            Glide.with(binding.root.context)
                .load(imageUrl)
                .error(R.drawable.outline_error_24)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.imageSlide)
        }

    }

    override fun getItemCount(): Int = imageUrlList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {

        val binding = ViewpagerItemImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {

        holder.setData(imageUrlList[position])
    }

}