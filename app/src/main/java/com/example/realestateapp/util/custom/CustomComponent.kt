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
    }

    fun setData(model: Data) = with(binding){
        "${model.city}/${model.district}/${model.neighborhood}".also { txtAdress.text = it }
        " ${ model.price }₺".also { txtPrice.text = it }
        txtCreatedDate.text = model.createdDate
        txtBath.text = model.bathCount.toString()
        txtRoom.text=model.roomCount.toString()
        "${model.gross} brüt m2".also { txtGross.text = it }
        "${model.net} net m2".also { txtNet.text = it }

    }
}
