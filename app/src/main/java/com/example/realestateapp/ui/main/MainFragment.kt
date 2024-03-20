package com.example.realestateapp.ui.main


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
class MainFragment() : BaseFragment<FragmentMainBinding, ViewModel>() {
    override fun getLayoutId(): Int = R.layout.fragment_main

    override val viewModel: MainViewModel by viewModels()

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
    }


    private fun observeUIState() {
        lifecycleScope.launch {
            viewModel.listState.collect {
                when (it) {
                    is NetworkResponseState.Loading -> showProgress()
                    is NetworkResponseState.Success -> {
                        getDataBinding().rvHouseList.adapter = adapter
                        adapter.submitList(it.result?.data)
                        hideProgress()
                    }

                    is NetworkResponseState.Error -> {
                        //showProgress()
                        hideProgress()
                        Toast.makeText(requireContext(), it.exception, Toast.LENGTH_LONG)
                            .show()
                    }

                    else -> Unit
                }
            }
        }

    }
}