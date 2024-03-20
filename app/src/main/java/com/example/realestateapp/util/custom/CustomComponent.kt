package com.example.realestateapp.util.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.realestateapp.R
import com.example.realestateapp.data.model.Data
import com.example.realestateapp.databinding.CustomComponentBinding


class CustomComponent @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet? = null,
    defAttr: Int = 0
) : FrameLayout(context, attributes, defAttr) {

    private val binding: CustomComponentBinding

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = CustomComponentBinding.inflate(inflater)
        addView(binding.root)

        val attrs = context.obtainStyledAttributes(attributes, R.styleable.CustomComponent)
        binding.apply {
            txtAdress.text = attrs.getString(R.styleable.CustomComponent_district)


           //  executePendingBindings()
        }
        attrs.recycle()
    }

    fun setModel(model: Data){
        binding.txtAdress.text = model.district
        binding.txtPrice.text = model.price
    }
    private fun readHouseListFromResource(resId: Int): List<String> {
        val houseArray = resources.getStringArray(resId)

        return houseArray.toList()
    }
}
