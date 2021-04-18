package com.wholedetail.react_shop_android.base

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import com.wholedetail.react_shop_android.utils.SwipeRefreshHandlerDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.CoroutineContext

abstract class RefreshableViewModel : ViewModel() {

    protected lateinit var refreshHandler: CoroutineContext
        private set


    fun initRefreshHandler(changeRefreshingState: (Boolean) -> Unit) {
        val changeRefreshingStatePost: (Boolean) -> Unit = { state: Boolean ->
            Handler(Looper.getMainLooper()).post { changeRefreshingState(state) }
        }

        refreshHandler = SwipeRefreshHandlerDispatcher(changeRefreshingStatePost) +
            CoroutineExceptionHandler { _, throwable ->
                throwable.printStackTrace()
                changeRefreshingState(false)
            }
    }
}