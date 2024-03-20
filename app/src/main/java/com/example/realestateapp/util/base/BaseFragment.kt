package com.example.realestateapp.util.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.realestateapp.util.custom.CustomProgressDialog

abstract class BaseFragment<DataBinding : ViewDataBinding, V : ViewModel> : Fragment() {

    private lateinit var dataBinding: DataBinding
    private lateinit var progressDialog: CustomProgressDialog
    abstract val viewModel: V

    abstract fun getLayoutId(): Int
    abstract fun onFragmentStarted()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
       setErrorMessage()
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFragmentStarted()
    }

    fun getDataBinding() = dataBinding

    fun setErrorMessage(message: String = "", isCanceled: Boolean = false){
        progressDialog = CustomProgressDialog(message,isCanceled)
    }

    fun showProgress() {
        progressDialog.show(
            requireActivity().supportFragmentManager,
            CustomProgressDialog()::class.java.name
        )
    }

    fun hideProgress() {
        try {
            progressDialog.dismiss()
        } catch (e: Exception) {
            Log.e("Error: ", e.message.toString())
        }
    }


}