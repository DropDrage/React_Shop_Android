package com.wholedetail.react_shop_android.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.wholedetail.react_shop_android.R

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private lateinit var viewModel: RegistrationFragmentViewModel


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegistrationFragmentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}