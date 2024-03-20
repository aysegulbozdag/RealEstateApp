package com.example.realestateapp.ui.detail

import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.realestateapp.R
import com.example.realestateapp.databinding.FragmentDetailBinding
import com.example.realestateapp.util.base.BaseFragment

class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {

    override val viewModel: DetailViewModel by viewModels()
    val args: DetailFragmentArgs by navArgs()
    override fun getLayoutId(): Int = R.layout.fragment_detail

    override fun onFragmentStarted() {

        getDataBinding().imageUrl = args.viewData.images[0]
        getDataBinding().include.viewData=args.viewData


    }

}