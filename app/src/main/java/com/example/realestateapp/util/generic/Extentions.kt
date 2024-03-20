package com.example.realestateapp.util.generic

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.realestateapp.data.model.Data
import com.example.realestateapp.util.custom.customcomponent.CustomComponent

@BindingAdapter("app:setModel")
fun CustomComponent.setModel(model: Data) = setData(model)

@BindingAdapter("loadImage")
fun customBinding(iv: ImageView, url:String?) {
    Glide.with(iv.context)
        .load(url)
        .into(iv)
}