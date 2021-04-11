package com.wholedetail.react_shop_android.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.wholedetail.react_shop_android.R

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var viewModel: LoginFragmentViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginFragmentViewModel::class.java)
        // TODO: Use the ViewModel
    }

    companion object {
        const val PARAM_EMAIL = "email"
        const val PARAM_AUTH_TOKEN_TYPE = "param_auth_token_type"
    }
}
