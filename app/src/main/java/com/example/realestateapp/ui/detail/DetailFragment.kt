package com.example.realestateapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.realestateapp.R
import com.example.realestateapp.databinding.FragmentDetailBinding
import com.example.realestateapp.ui.main.MainViewModel
import com.example.realestateapp.util.base.BaseFragment

class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {

    override val viewModel: DetailViewModel by viewModels()
    override fun getLayoutId(): Int = R.layout.fragment_detail

    override fun onFragmentStarted() {
        requireActivity().enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(getDataBinding().containerll) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }    }

}