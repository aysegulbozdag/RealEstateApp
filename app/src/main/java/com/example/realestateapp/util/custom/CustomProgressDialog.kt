package com.example.realestateapp.util.custom

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.realestateapp.databinding.CustomProgressDialogBinding

class CustomProgressDialog() : AppCompatDialogFragment() {

    private lateinit var binding: CustomProgressDialogBinding

    private var mProgressContentText: String = ""
    private var mIsProgressCancelable: Boolean = false

    constructor(progressContentText: String, isDialogCancelable: Boolean) : this() {
        this.mProgressContentText = progressContentText
        this.mIsProgressCancelable = isDialogCancelable
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder: AlertDialog.Builder = AlertDialog.Builder(it)
            binding = CustomProgressDialogBinding.inflate(LayoutInflater.from(context))
            val alertDialog = builder.setView(binding.root).create()
            alertDialog.setCanceledOnTouchOutside(mIsProgressCancelable)
            alertDialog.show()
            alertDialog
        } ?: throw IllegalStateException("Activity cannot be null")

    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}