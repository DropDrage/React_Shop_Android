package com.wholedetail.react_shop_android.model

/**
 * Used for displaying t-shirt in list.
 */
data class TShirtMinified(
    val id: Long,
    val title: String,
    val price: Float,
    val rating: Float,
    val imageUrl: String?,
    val author: UserMinified
)