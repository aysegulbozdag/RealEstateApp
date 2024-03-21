package com.example.realestateapp.ui.main


import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.realestateapp.R
import com.example.realestateapp.data.NetworkResponseState
import com.example.realestateapp.data.model.Data
import com.example.realestateapp.databinding.FragmentMainBinding
import com.example.realestateapp.util.base.BaseDiffUtilItemCallback
import com.example.realestateapp.util.base.BaseFragment
import com.example.realestateapp.util.generic.GenericAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding, ViewModel>() {
    override fun getLayoutId(): Int = R.layout.fragment_main

    override val viewModel: MainViewModel by viewModels()
    private var dataList = mutableListOf<Data>()

    private val adapter by lazy {
        GenericAdapter(
            viewModel,
            R.layout.rv_item,
            BaseDiffUtilItemCallback<Data>()
        ) {
            onClick { item ->
                val action = MainFragmentDirections.actionMainFragmentToDetailFragment(item)
                findNavController().navigate(action)
            }
        }
    }

    override fun onFragmentStarted() {
        observeUIState()
        onTextChanged()
    }

    private fun initAdapter(data: List<Data>) = with(getDataBinding()) {
        rvHouseList.adapter = adapter
        adapter.submitList(data)
    }

    private fun onTextChanged() = with(getDataBinding()) {
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if ((s.toString() == "").not()) {
                    updateRecyclerView(s.toString())
                } else initAdapter(dataList)
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    fun updateRecyclerView(query: String) {
        val filteredList = viewModel.searchList(query, dataList)
        initAdapter(filteredList)
    }


    private fun observeUIState() {
        lifecycleScope.launch {
            viewModel.listState.collect {
                when (it) {
                    is NetworkResponseState.Loading -> showProgress()
                    is NetworkResponseState.Success -> {
                        it.result?.data?.let {
                            dataList.addAll(it.toMutableList())
                            initAdapter(it)
                        }
                        hideProgress()
                    }

                    is NetworkResponseState.Error -> {
                        hideProgress()
                        Toast.makeText(requireContext(), it.exception, Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        dataList.clear()
    }
}