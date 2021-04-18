package com.wholedetail.react_shop_android.ui

import androidx.lifecycle.viewModelScope
import com.wholedetail.react_shop_android.base.RefreshableViewModel
import com.wholedetail.react_shop_android.model.TShirt
import com.wholedetail.react_shop_android.network.TShirtService
import com.wholedetail.react_shop_android.utils.extension.defaultMutableSharedFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TShirtViewModel(private val tShirtId: Long, private val tShirtService: TShirtService) : RefreshableViewModel() {

    private val _tShirt = defaultMutableSharedFlow<TShirt>()


    fun updateTShirt() {
        viewModelScope.launch(Dispatchers.IO) {
            _tShirt.tryEmit(tShirtService.getTShirt(tShirtId))
        }
    }


}