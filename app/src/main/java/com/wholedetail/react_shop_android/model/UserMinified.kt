package com.wholedetail.react_shop_android.model

import com.google.gson.annotations.SerializedName

data class UserMinified(
    val id: Long,
    val username: String,
    val avatarURL: String,
    @SerializedName("blocked") val isBlocked: Boolean
)