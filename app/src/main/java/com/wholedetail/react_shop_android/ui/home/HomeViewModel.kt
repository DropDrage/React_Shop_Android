package com.wholedetail.react_shop_android.ui.home

import androidx.lifecycle.viewModelScope
import com.wholedetail.react_shop_android.base.RefreshableViewModel
import com.wholedetail.react_shop_android.model.TShirtMinified
import com.wholedetail.react_shop_android.model.TShirtSize
import com.wholedetail.react_shop_android.model.Tag
import com.wholedetail.react_shop_android.model.Topic
import com.wholedetail.react_shop_android.model.User
import com.wholedetail.react_shop_android.network.TShirtService
import com.wholedetail.react_shop_android.utils.extension.defaultMutableSharedFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val tShirtService: TShirtService) : RefreshableViewModel() {

    private val _tShirts = defaultMutableSharedFlow<List<TShirtMinified>>()
    val tShirts: SharedFlow<List<TShirtMinified>> = _tShirts


    fun updateTShirts(
        authors: List<User>? = null,
        topic: Topic? = null,
        tag: Tag? = null,
        sizes: List<TShirtSize>? = null,
        priceMin: Float? = null,
        priceMax: Float? = null,
    ) {
        viewModelScope.launch(Dispatchers.IO + refreshHandler) {
            _tShirts.tryEmit(tShirtService.getAll(authors, topic, tag, sizes, priceMin, priceMax))
        }
    }

}