package com.wholedetail.react_shop_android.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.wholedetail.react_shop_android.R
import com.wholedetail.react_shop_android.databinding.FragmentHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModel() { parametersOf(binding.rootSRL::setRefreshing) }

    private val binding: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            tShirtsRV.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            tShirtsRV.adapter = TShirtsAdapter()

            rootSRL.setOnRefreshListener {
                viewModel.updateTShirts()
            }
        }

        viewModel.run {
            lifecycleScope.launchWhenStarted {
                withContext(Dispatchers.IO) {
                    tShirts.onEach { (binding.tShirtsRV.adapter as TShirtsAdapter).tShirts = it }
                        .flowOn(Dispatchers.Main)
                        .launchIn(lifecycleScope)
                }

                updateTShirts()
            }
        }
    }
}