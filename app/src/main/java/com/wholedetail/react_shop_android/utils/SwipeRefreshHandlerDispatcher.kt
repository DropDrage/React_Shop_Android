package com.wholedetail.react_shop_android.utils

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.isActive
import kotlinx.coroutines.scheduling.ExperimentalCoroutineDispatcher
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext

@OptIn(InternalCoroutinesApi::class)
class SwipeRefreshHandlerDispatcher(private val swipeRefreshingChange: (Boolean) -> Unit) :
    ExperimentalCoroutineDispatcher() {

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        super.dispatch(context, block)
        swipeRefreshingChange(context.isActive)
    }

    @InternalCoroutinesApi
    override fun releaseInterceptedContinuation(continuation: Continuation<*>) {
        super.releaseInterceptedContinuation(continuation)
        swipeRefreshingChange(false)
    }

    override fun close() {
        super.close()
        swipeRefreshingChange(false)
    }
}