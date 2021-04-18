package com.wholedetail.react_shop_android.utils.extension

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

fun <T> ViewModel.defaultMutableSharedFlow() = MutableSharedFlow<T>(1, onBufferOverflow = BufferOverflow.DROP_OLDEST)