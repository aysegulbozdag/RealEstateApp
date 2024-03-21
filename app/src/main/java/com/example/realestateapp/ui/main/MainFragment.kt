package com.example.realestateapp.ui.main


import android.widget.Toast
import androidx.core.widget.doOnTextChanged
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

    private val adapter by lazy {
        GenericAdapter(
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
        etSearch.doOnTextChanged { text, start, before, count ->
            if ((text.toString() == "").not()) {
                updateList(text.toString())
            } else initAdapter(viewModel.dataList)
        }
    }

    private fun updateList(query: String) = with(viewModel) {
        val filteredList = viewModel.searchList(query, viewModel.dataList)
        initAdapter(filteredList)
    }


    private fun observeUIState() = with(viewModel) {
        lifecycleScope.launch {
            viewModel.listState.collect {
                when (it) {
                    is NetworkResponseState.Loading -> showProgress()
                    is NetworkResponseState.Success -> {
                        it.result?.data?.let { data ->
                            dataList.addAll(data.toMutableList())
                            initAdapter(data)
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
        viewModel.clearList()
    }
}