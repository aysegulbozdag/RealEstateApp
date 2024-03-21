package com.example.realestateapp.util.generic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.realestateapp.BR
import com.example.realestateapp.util.base.BaseDiffUtilItemCallback


class GenericAdapter<T : Any>(
    @LayoutRes val layoutId: Int,
    private val diffCallback: DiffUtil.ItemCallback<T> = BaseDiffUtilItemCallback<T>(),
    private val callBack: ViewHolder<T>.() -> Unit,
) : ListAdapter<T, ViewHolder<T>>(diffCallback) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder<T> {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            layoutInflater, layoutId, viewGroup, false
        )
        return ViewHolder<T>(binding).apply(callBack)
    }

    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {
        holder.bind(currentList[position])
    }

}

class ViewHolder<T : Any>(
    private val binding: ViewDataBinding
) :
    RecyclerView.ViewHolder(binding.root) {
    private lateinit var item: T

    fun bind(bindingItem: T) {
        item = bindingItem
        binding.run {
            binding.setVariable(BR.viewData, bindingItem)
            executePendingBindings()
        }
    }

    fun onClick(block: (item: T) -> Unit) {
        binding.root.setOnClickListener { block.invoke(item) }
    }
}