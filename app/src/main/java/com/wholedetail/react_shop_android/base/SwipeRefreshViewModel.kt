package com.wholedetail.react_shop_android.base

import androidx.lifecycle.ViewModel
import com.wholedetail.react_shop_android.utils.SwipeRefreshHandlerDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler

/**
 * @property changeRefreshingState used to start and stop refreshing.
 */
abstract class SwipeRefreshViewModel(private val changeRefreshingState: (Boolean) -> Unit) : ViewModel() {
    protected val refreshHandler =
        SwipeRefreshHandlerDispatcher(changeRefreshingState) +
            CoroutineExceptionHandler { _, throwable ->
                throwable.printStackTrace()
                changeRefreshingState(false)
            }
}