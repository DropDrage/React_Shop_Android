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
import com.wholedetail.react_shop_android.utils.BottomSpaceItemDecoration
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home), AndroidScopeComponent {

    override val scope by fragmentScope()

    private val viewModel by viewModel<HomeViewModel>()
    private val binding by viewBinding(FragmentHomeBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            initTshirtsRV()

            rootSRL.setOnRefreshListener {
                viewModel.updateTShirts()
            }
        }

        viewModel.run {
            initRefreshHandler(binding.rootSRL::setRefreshing)

            lifecycleScope.launchWhenResumed {
                tShirts.onEach { (binding.tShirtsRV.adapter as TShirtsAdapter).tShirts = it }.launchIn(lifecycleScope)

                if (tShirts.replayCache.isEmpty()) {
                    updateTShirts()
                }
            }
        }
    }


    private fun FragmentHomeBinding.initTshirtsRV() {
        tShirtsRV.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = TShirtsAdapter()
            addItemDecoration(BottomSpaceItemDecoration(resources.getDimensionPixelOffset(R.dimen.margin_default)))
        }
    }
}
