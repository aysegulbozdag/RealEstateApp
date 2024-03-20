package com.example.realestateapp.util.generic

import androidx.databinding.BindingAdapter
import com.example.realestateapp.data.model.Data
import com.example.realestateapp.util.custom.CustomComponent

@BindingAdapter("app:setModel")
fun CustomComponent.setModel(model: Data) = setData(model)