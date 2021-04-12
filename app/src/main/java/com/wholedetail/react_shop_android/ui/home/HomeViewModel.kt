package com.wholedetail.react_shop_android.ui.home

import androidx.lifecycle.viewModelScope
import com.wholedetail.react_shop_android.base.SwipeRefreshViewModel
import com.wholedetail.react_shop_android.model.TShirt
import com.wholedetail.react_shop_android.model.TShirtSize
import com.wholedetail.react_shop_android.model.Tag
import com.wholedetail.react_shop_android.model.Topic
import com.wholedetail.react_shop_android.model.User
import com.wholedetail.react_shop_android.network.TShirtService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(changeRefreshingState: (Boolean) -> Unit, private val tShirtService: TShirtService) :
    SwipeRefreshViewModel(changeRefreshingState) {

    private val _tShirts = MutableStateFlow(emptyList<TShirt>())
    val tShirts: Flow<List<TShirt>> = _tShirts


    fun updateTShirts(
        authors: List<User>? = null,
        topic: Topic? = null,
        tag: Tag? = null,
        sizes: List<TShirtSize>? = null,
        priceMin: Float? = null,
        priceMax: Float? = null,
    ) {
        viewModelScope.launch(Dispatchers.IO + refreshHandler) {
            _tShirts.value = tShirtService.getAll(authors, topic, tag, sizes, priceMin, priceMax)
        }
    }

}