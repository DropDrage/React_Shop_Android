package com.wholedetail.react_shop_android.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.wholedetail.react_shop_android.R
import com.wholedetail.react_shop_android.databinding.FragmentTShirtBinding
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class TShirtFragment : Fragment(R.layout.fragment_t_shirt), AndroidScopeComponent {

    override val scope by fragmentScope()

    private val args by navArgs<TShirtFragmentArgs>()
    private val viewModel by viewModel<TShirtViewModel>() { parametersOf(args.tShirtId) }
    private val binding by viewBinding(FragmentTShirtBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}