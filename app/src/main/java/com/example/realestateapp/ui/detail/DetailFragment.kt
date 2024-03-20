package com.example.realestateapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.realestateapp.R

class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (requireActivity() as? AppCompatActivity)?.supportActionBar?.apply {
            // Geri tuşunu göster
            setDisplayHomeAsUpEnabled(true)
            // Anasayfa simgesini göster
           // setDisplayShowHomeEnabled(true)
        }
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }



}